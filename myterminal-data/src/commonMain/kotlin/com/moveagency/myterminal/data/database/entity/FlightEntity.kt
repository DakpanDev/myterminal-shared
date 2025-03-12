package com.moveagency.myterminal.data.database.entity

import androidx.room.*
import com.moveagency.myterminal.data.database.Converters
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

@Entity(tableName = "flight")
@TypeConverters(Converters::class)
data class FlightEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val destination: String,

    @ColumnInfo
    val states: States?,

    @ColumnInfo(name = "departure_datetime")
    val departureDateTime: LocalDateTime,

    @ColumnInfo
    val terminal: Int?,

    @ColumnInfo(name = "checkin_rows")
    val checkinRows: CheckinRows?,

    @ColumnInfo
    val gate: String?,

    @ColumnInfo(name = "checkin_closing_time")
    val checkinClosingTime: LocalTime?,

    @ColumnInfo(name = "gate_opening_time")
    val gateOpeningTime: LocalTime?,

    @ColumnInfo(name = "boarding_time")
    val boardingTime: LocalTime?,

    @ColumnInfo(name = "actual_departure_time")
    val actualDepartureTime: LocalTime?,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: LocalDateTime,
)

data class States(
    val values: List<String>,
)

data class CheckinRows(
    val values: List<String>,
)
