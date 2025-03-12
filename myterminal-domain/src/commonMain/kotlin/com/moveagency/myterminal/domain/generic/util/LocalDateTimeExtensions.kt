package com.moveagency.myterminal.domain.generic.util

import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeComponents

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.Companion.parseOffset(offsetIso: String) = DateTimeComponents
    .Formats.ISO_DATE_TIME_OFFSET
    .parse(offsetIso)
    .toInstantUsingOffset()
    .toLocalDateTime(TimeZone.currentSystemDefault())

fun LocalDate.Companion.now(): LocalDate {
    return LocalDateTime.now().date
}

fun LocalDate.Companion.fromEpochMilliseconds(millis: Long) =
    Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault()).date
