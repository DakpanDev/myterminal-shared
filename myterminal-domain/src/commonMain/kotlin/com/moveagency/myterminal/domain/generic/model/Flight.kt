package com.moveagency.myterminal.domain.generic.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class Flight(
    val id: String,
    val name: String,
    val destination: String,
    val states: List<String>?,
    val departureDateTime: LocalDateTime,
    val terminal: Int?,
    val checkinRows: List<String>?,
    val gate: String?,
    val checkinClosingTime: LocalTime?,
    val gateOpeningTime: LocalTime?,
    val boardingTime: LocalTime?,
    val actualDepartureTime: LocalTime?,
    val lastUpdated: LocalDateTime,
    val isBookmarked: Boolean,
)
