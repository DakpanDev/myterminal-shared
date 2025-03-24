package com.moveagency.myterminal.domain.generic.repository

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.util.now
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface FlightsRepository {

    suspend fun fetchFlights(date: LocalDate = LocalDate.now())
    fun observeFlights(date: LocalDate): Flow<List<Flight>>
    fun observeFlightDetails(id: String): Flow<Flight>

    fun observeAllBookmarks(): Flow<List<Flight>>
    fun observeBookmark(id: String): Flow<Flight>
    suspend fun bookmarkFlight(id: String)
    suspend fun unBookmarkFlight(id: String)
}
