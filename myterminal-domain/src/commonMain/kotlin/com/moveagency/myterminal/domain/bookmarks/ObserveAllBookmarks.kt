package com.moveagency.myterminal.domain.bookmarks

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface ObserveAllBookmarks {

    @Throws(Throwable::class)
    operator fun invoke(): Flow<List<Flight>>
}

@Factory
class ObserveAllBookmarksImpl(
    private val repository: FlightsRepository,
) : ObserveAllBookmarks {

    override operator fun invoke() = repository.observeAllBookmarks()
}
