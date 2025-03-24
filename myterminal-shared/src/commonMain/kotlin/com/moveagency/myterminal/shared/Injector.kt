package com.moveagency.myterminal.shared

import com.moveagency.myterminal.domain.bookmarks.ObserveAllBookmarks
import com.moveagency.myterminal.domain.bookmarks.ObserveBookmark
import com.moveagency.myterminal.domain.details.*
import com.moveagency.myterminal.domain.home.FetchMoreFlights
import com.moveagency.myterminal.domain.home.ObserveFlights
import org.koin.mp.KoinPlatform

fun observeFlights(): ObserveFlights = KoinPlatform.getKoin().get()
fun fetchMoreFlights(): FetchMoreFlights = KoinPlatform.getKoin().get()
fun observeFlightDetails(): ObserveFlightDetails = KoinPlatform.getKoin().get()

fun observeAllBookmarks(): ObserveAllBookmarks = KoinPlatform.getKoin().get()
fun observeBookmark(): ObserveBookmark = KoinPlatform.getKoin().get()

fun bookmarkFlight(): BookmarkFlight = KoinPlatform.getKoin().get()
fun unBookmarkFlight(): UnBookmarkFlight = KoinPlatform.getKoin().get()
