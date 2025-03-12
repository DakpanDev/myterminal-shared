package com.moveagency.myterminal.data.database.mapper

import com.moveagency.myterminal.data.database.entity.*
import com.moveagency.myterminal.domain.generic.model.Flight
import org.koin.core.annotation.Factory

@Factory
class FlightsEntityMapper {
    
    fun mapEntityToFlight(entity: FlightEntity) = Flight(
        id = entity.id,
        name = entity.name,
        destination = entity.destination,
        states = entity.states?.values,
        departureDateTime = entity.departureDateTime,
        terminal = entity.terminal,
        checkinRows = entity.checkinRows?.values,
        gate = entity.gate,
        checkinClosingTime = entity.checkinClosingTime,
        gateOpeningTime = entity.gateOpeningTime,
        boardingTime = entity.boardingTime,
        actualDepartureTime = entity.actualDepartureTime,
        lastUpdated = entity.lastUpdated,
        isBookmarked = true,
    )

    fun mapFlightToEntity(flight: Flight) = FlightEntity(
        id = flight.id,
        name = flight.name,
        destination = flight.destination,
        states = flight.states?.let { States(it) },
        departureDateTime = flight.departureDateTime,
        terminal = flight.terminal,
        checkinRows = flight.checkinRows?.let { CheckinRows(it) },
        gate = flight.gate,
        checkinClosingTime = flight.checkinClosingTime,
        gateOpeningTime = flight.gateOpeningTime,
        boardingTime = flight.boardingTime,
        actualDepartureTime = flight.actualDepartureTime,
        lastUpdated = flight.lastUpdated
    )
}
