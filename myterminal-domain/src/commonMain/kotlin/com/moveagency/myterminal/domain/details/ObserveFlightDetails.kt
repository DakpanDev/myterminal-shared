package com.moveagency.myterminal.domain.details

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface ObserveFlightDetails {

    @Throws(Throwable::class)
    operator fun invoke(id: String): Flow<Flight>
}

@Factory
class ObserveFlightDetailsImpl(
    private val repository: FlightsRepository,
) : ObserveFlightDetails {

    override operator fun invoke(id: String) = repository.observeFlightDetails(id)
}
