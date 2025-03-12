package com.moveagency.myterminal.domain.home

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import com.moveagency.myterminal.domain.generic.util.now
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

@Factory
class ObserveFlights(
    private val repository: FlightsRepository,
) {

    suspend operator fun invoke(day: LocalDate = LocalDate.now()) = repository.observeFlights(day)
}
