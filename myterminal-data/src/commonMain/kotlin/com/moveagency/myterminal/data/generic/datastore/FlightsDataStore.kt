package com.moveagency.myterminal.data.generic.datastore

import com.moveagency.myterminal.data.generic.datastore.model.FlightsCache
import com.moveagency.myterminal.domain.generic.model.Flight
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface FlightsDataStore {

    fun updateFlights(date: LocalDate, flights: List<Flight>)
    fun observeFlights(): Flow<FlightsCache>
    fun getAllCachedFlights(): List<Flight>?
    fun getCachedFlightsByDate(date: LocalDate): List<Flight>?

    fun getFlightDetails(id: String): Flight?
    fun getBookmarkedDetails(id: String): Flight?

    fun observeBookmarks(): Flow<List<Flight>>
    fun getAllBookmarked(): List<Flight>

    fun bookmarkFlight(flight: Flight)
    fun unBookmarkFlight(flight: Flight)

    fun getHighestPage(date: LocalDate): Int?
    fun incrementPage(date: LocalDate)
}
