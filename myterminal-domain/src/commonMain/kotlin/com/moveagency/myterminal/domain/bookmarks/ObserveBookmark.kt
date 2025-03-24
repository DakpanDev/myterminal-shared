package com.moveagency.myterminal.domain.bookmarks

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface ObserveBookmark {

    @Throws(Throwable::class)
    operator fun invoke(flightId: String): Flow<Flight>
}

@Factory
class ObserveBookmarkImpl(
    private val repository: FlightsRepository,
) : ObserveBookmark {

    override operator fun invoke(flightId: String) = repository.observeBookmark(flightId)
}
