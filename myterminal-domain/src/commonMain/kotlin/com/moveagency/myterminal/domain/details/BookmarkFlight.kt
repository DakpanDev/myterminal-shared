package com.moveagency.myterminal.domain.details

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import org.koin.core.annotation.Factory

interface BookmarkFlight {

    @Throws(Throwable::class)
    suspend operator fun invoke(id: String)
}

@Factory
class BookmarkFlightImpl(
    private val repository: FlightsRepository,
) : BookmarkFlight {

    override suspend operator fun invoke(id: String) = repository.bookmarkFlight(id)
}
