package com.moveagency.myterminal.domain.home

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import com.moveagency.myterminal.domain.generic.util.now
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

interface ObserveFlights {

    @Throws(IllegalStateException::class)
    operator fun invoke(day: LocalDate = LocalDate.now()): Flow<List<Flight>>
}

@Factory
class ObserveFlightsImpl(
    private val repository: FlightsRepository,
) : ObserveFlights {

    override operator fun invoke(day: LocalDate) = repository.observeFlights(day)
}
