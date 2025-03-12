package com.moveagency.myterminal.data.generic.mapper

import com.moveagency.myterminal.data.generic.remote.response.*
import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.util.parseOffset
import kotlinx.datetime.LocalDateTime
import org.koin.core.annotation.Factory

@Factory
class FlightsResponseMapper {

    fun mapListResponseToDomain(response: FlightListResponse) = response.flights
        ?.mapNotNull(::mapResponseToDomain)
        ?: listOf()

    private fun mapResponseToDomain(response: FlightDetailsResponse): Flight? {
        return Flight(
            id = response.id ?: return null,
            name = response.flightName ?: return null,
            destination = response.route?.destinations?.last() ?: return null,
            states = response.status?.flightStates,
            departureDateTime = response.departureDateTime?.let(::mapStringToDateTime) ?: return null,
            terminal = response.terminal,
            checkinRows = response.checkinRows?.let(::mapCheckinAllocationsToPositions),
            gate = response.gate,
            checkinClosingTime = response.checkinRows?.allocations
                ?.firstOrNull()
                ?.endTime
                ?.let { mapStringToDateTime(it).time },
            gateOpeningTime = response.gateOpeningTime?.let { mapStringToDateTime(it).time },
            boardingTime = response.boardingTime?.let { mapStringToDateTime(it).time },
            actualDepartureTime = response.actualDepartureTime?.let { mapStringToDateTime(it).time },
            lastUpdated = response.lastUpdated?.let(::mapStringToDateTime) ?: return null,
            isBookmarked = false,
        )
    }

    private fun mapStringToDateTime(value: String) = LocalDateTime.parseOffset(value)

    private fun mapCheckinAllocationsToPositions(response: CheckinAllocationsResponse): List<String>? {
        return response.allocations
            ?.mapNotNull { it.rows }
            ?.mapNotNull { it.rows }
            ?.flatten()
            ?.mapNotNull { it.position }
            ?: return null
    }
}
