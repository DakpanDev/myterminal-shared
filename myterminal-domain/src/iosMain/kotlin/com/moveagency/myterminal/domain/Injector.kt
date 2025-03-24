package com.moveagency.myterminal.domain

import com.moveagency.myterminal.domain.bookmarks.ObserveAllBookmarks
import com.moveagency.myterminal.domain.bookmarks.ObserveBookmark
import com.moveagency.myterminal.domain.details.*
import com.moveagency.myterminal.domain.home.FetchMoreFlights
import com.moveagency.myterminal.domain.home.ObserveFlights
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object Injector : KoinComponent {

    val observeFlights: ObserveFlights by inject()
    val fetchMoreFlight: FetchMoreFlights by inject()
    val observeFlightDetails: ObserveFlightDetails by inject()

    val observeAllBookmarks: ObserveAllBookmarks by inject()
    val observeBookmark: ObserveBookmark by inject()

    val bookmarkFlight: BookmarkFlight by inject()
    val unBookmarkFlight: UnBookmarkFlight by inject()
}
