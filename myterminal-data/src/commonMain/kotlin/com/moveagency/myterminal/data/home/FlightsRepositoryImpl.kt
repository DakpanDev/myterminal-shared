@file:OptIn(ExperimentalCoroutinesApi::class)
package com.moveagency.myterminal.data.home

import com.moveagency.myterminal.data.generic.datastore.DestinationsDataStore
import com.moveagency.myterminal.data.generic.datastore.FlightsDataStore
import com.moveagency.myterminal.data.generic.mapper.FlightsResponseMapper
import com.moveagency.myterminal.data.generic.remote.SchipholService
import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

@Factory
class FlightsRepositoryImpl(
    private val api: SchipholService,
    private val flightsDatastore: FlightsDataStore,
    private val destinationsDataStore: DestinationsDataStore,
    private val mapper: FlightsResponseMapper,
) : FlightsRepository {

    override suspend fun fetchFlights(date: LocalDate) {
        val nextPage = flightsDatastore.getHighestPage(date)?.plus(1) ?: 0
        val response = api.retrieveFlights(page = nextPage, date = date)

        val bookmarked = getAllBookmarked().map { it.id }
        val oldFlights = flightsDatastore.getCachedFlightsByDate(date) ?: emptyList()
        val newFlights = mapper.mapListResponseToDomain(response).map {
            it.copy(
                destination = getDestination(it.destination),
                isBookmarked = it.id in bookmarked,
            )
        }
        val updatedFlights = oldFlights + newFlights

        flightsDatastore.updateFlights(date, updatedFlights)
        flightsDatastore.incrementPage(date)
    }

    override fun observeFlights(date: LocalDate): Flow<List<Flight>> {
        return flightsDatastore.observeFlights().flatMapLatest {
            val flights = it[date]
            if (!flights.isNullOrEmpty()) {
                flow { emit(flights) }
            } else {
                fetchFlights(date)
                emptyFlow()
            }
        }
    }

    override fun observeFlightDetails(id: String): Flow<Flight> {
        return flightsDatastore.observeFlights().flatMapLatest { flowValue ->
            val flight = flowValue.values.flatten().firstOrNull { it.id == id }
                ?: error("Flight with id $id was not found")
            flow { emit(flight) }
        }
    }

    override fun observeAllBookmarks() = flow {
        flightsDatastore.observeBookmarks().collect { emit(it) }
    }

    override fun observeBookmark(id: String): Flow<Flight> = flow {
        flightsDatastore.observeBookmarks().collect {
            val flight = it.firstOrNull { flight -> flight.id == id }
            if (flight != null) emit(flight)
        }
    }

    override suspend fun bookmarkFlight(id: String) {
        val flight = flightsDatastore.getFlightDetails(id)
            ?: error("Flight with id $id could not be found")
        val date = flight.departureDateTime.date
        flightsDatastore.bookmarkFlight(flight)
        flightsDatastore.getCachedFlightsByDate(date)
            ?.map { if (it.id != id) it else it.copy(isBookmarked = true) }
            ?.also { flightsDatastore.updateFlights(date, it) }
    }

    override suspend fun unBookmarkFlight(id: String) {
        val flight = flightsDatastore.getBookmarkedDetails(id)
            ?: error("Bookmarked flight with id $id could not be found")
        val date = flight.departureDateTime.date
        flightsDatastore.unBookmarkFlight(flight)
        flightsDatastore.getAllCachedFlights()
            ?.map { if (it.id != id) it else it.copy(isBookmarked = false) }
            ?.let { flightsDatastore.updateFlights(date, it) }
    }

    private suspend fun getAllBookmarked() = flightsDatastore.getAllBookmarked()

    private suspend fun getDestination(iata: String): String {
        val storedDestination = destinationsDataStore.getDestinationValue(iata)
        if (storedDestination != null) return storedDestination

        val destination = try {
            val response = api.retrieveDestination(iata)
            when {
                response.city != null -> response.city
                response.publicName?.english != null -> response.publicName.english
                response.country != null -> response.country
                else -> iata
            }.also {
                destinationsDataStore.storeDestination(iata, it)
            }
        } catch (exception: Exception) {
            iata
        }

        return destination
    }
}
