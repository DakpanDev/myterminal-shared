package com.moveagency.myterminal.domain.home

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

interface FetchMoreFlights {

    @Throws(Throwable::class)
    suspend operator fun invoke(date: LocalDate)
}

@Factory
class FetchMoreFlightsImpl(
    private val repository: FlightsRepository,
) : FetchMoreFlights {

    override suspend operator fun invoke(date: LocalDate) = repository.fetchFlights(date)
}
