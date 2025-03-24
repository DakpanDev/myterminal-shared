package com.moveagency.myterminal.domain.details

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import org.koin.core.annotation.Factory

interface UnBookmarkFlight {

    @Throws(Throwable::class)
    suspend operator fun invoke(id: String)
}

@Factory
class UnBookmarkFlightImpl(
    private val repository: FlightsRepository,
) : UnBookmarkFlight {

    override suspend operator fun invoke(id: String) = repository.unBookmarkFlight(id)
}
