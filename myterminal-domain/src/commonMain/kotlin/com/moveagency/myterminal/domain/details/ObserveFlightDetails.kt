package com.moveagency.myterminal.domain.details

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveFlightDetails(
    private val repository: FlightsRepository,
) {

    operator fun invoke(id: String) = repository.observeFlightDetails(id)
}
