package com.moveagency.myterminal.domain.home

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

@Factory
class FetchMoreFlights(
    private val repository: FlightsRepository,
) {

    suspend operator fun invoke(date: LocalDate) = repository.fetchFlights(date)
}
