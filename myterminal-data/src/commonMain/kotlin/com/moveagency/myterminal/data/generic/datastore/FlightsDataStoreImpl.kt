package com.moveagency.myterminal.data.generic.datastore

import com.moveagency.myterminal.data.database.MyTerminalDatabase
import com.moveagency.myterminal.data.database.mapper.FlightsEntityMapper
import com.moveagency.myterminal.data.generic.datastore.model.FlightsCache
import com.moveagency.myterminal.domain.generic.model.Flight
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Singleton

@Singleton
class FlightsDataStoreImpl(
    private val database: MyTerminalDatabase,
    private val mapper: FlightsEntityMapper,
) : FlightsDataStore {

    private val cachedPages = mutableMapOf<LocalDate, Int?>()
    private val cachedFlights = MutableStateFlow<FlightsCache>(mapOf())
    private val bookmarkedFlights = MutableStateFlow<List<Flight>>(emptyList())
    private var retrievedBookmarks = false

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

    override suspend fun observeBookmarks(): Flow<List<Flight>> {
        if (!retrievedBookmarks) {
            val flights = database.flightsDao().getAll().map(mapper::mapEntityToFlight)
            bookmarkedFlights.value = flights
        }

        return bookmarkedFlights
    }

    override suspend fun getAllBookmarked() = database.flightsDao()
        .getAll()
        .map(mapper::mapEntityToFlight)

    override suspend fun bookmarkFlight(flight: Flight) {
        val entity = mapper.mapFlightToEntity(flight)
        database.flightsDao().insertFlight(entity)
        bookmarkedFlights.value = getAllBookmarked()
    }

    override suspend fun unBookmarkFlight(flight: Flight) {
        val entity = mapper.mapFlightToEntity(flight)
        database.flightsDao().delete(entity)
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
