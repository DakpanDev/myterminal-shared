package com.moveagency.myterminal.domain.bookmarks

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveAllBookmarks(
    private val repository: FlightsRepository,
) {

    operator fun invoke() = repository.observeAllBookmarks()
}
