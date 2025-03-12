package com.moveagency.myterminal.data.generic.datastore

import com.moveagency.myterminal.data.database.MyTerminalDatabase
import com.moveagency.myterminal.data.database.mapper.FlightsEntityMapper
import com.moveagency.myterminal.data.generic.datastore.model.FlightsCache
import com.moveagency.myterminal.domain.generic.model.Flight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Singleton

@Singleton
class FlightsDataStoreImpl(
    private val database: MyTerminalDatabase,
    private val mapper: FlightsEntityMapper,
) : FlightsDataStore {

    private val cachedPages = mutableMapOf<LocalDate, Int?>()
    private val cachedFlights = MutableStateFlow<FlightsCache>(mapOf())
    private val bookmarkedFlights by lazy {
        val flights = database.flightsDao().getAll().map(mapper::mapEntityToFlight)
        MutableStateFlow(flights)
    }

    override fun updateFlights(date: LocalDate, flights: List<Flight>) {
        val newFlights = cachedFlights.value.toMutableMap()
        newFlights[date] = flights
        cachedFlights.value = newFlights
    }

    override fun observeFlights(): Flow<FlightsCache> {
        return cachedFlights
    }

    override fun getAllCachedFlights() = allCachedFlights()

    override fun getCachedFlightsByDate(date: LocalDate): List<Flight>? {
        return cachedFlights.value[date]
    }

    override fun getFlightDetails(id: String) = allCachedFlights().firstOrNull { it.id == id }

    override fun getBookmarkedDetails(id: String): Flight? {
        return bookmarkedFlights.value.firstOrNull { it.id == id }
    }

    override fun observeBookmarks() = bookmarkedFlights

    override fun getAllBookmarked() = database.flightsDao()
        .getAll()
        .map(mapper::mapEntityToFlight)

    override fun bookmarkFlight(flight: Flight) = flight
        .let(mapper::mapFlightToEntity)
        .let(database.flightsDao()::insertFlight)
        .also { bookmarkedFlights.value = getAllBookmarked() }

    override fun unBookmarkFlight(flight: Flight) = flight
        .let(mapper::mapFlightToEntity)
        .let(database.flightsDao()::delete)
        .also {
            val newCache = getAllBookmarked() + flight.copy(isBookmarked = false)
            bookmarkedFlights.value = newCache
        }

    override fun getHighestPage(date: LocalDate): Int? {
        return cachedPages[date]
    }

    override fun incrementPage(date: LocalDate) {
        cachedPages[date] = cachedPages[date]?.plus(1) ?: 0
    }

    private fun allCachedFlights() = cachedFlights.value.values.flatten()
}
