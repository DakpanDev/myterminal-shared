package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightDetailsResponse(

    @SerialName("id")
    val id: String? = null,

    @SerialName("flightName")
    val flightName: String? = null,

    @SerialName("route")
    val route: RouteResponse? = null,

    @SerialName("publicFlightState")
    val status: FlightStateResponse? = null,

    @SerialName("scheduleDateTime")
    val departureDateTime: String? = null,

    @SerialName("terminal")
    val terminal: Int? = null,

    @SerialName("checkinAllocations")
    val checkinRows: CheckinAllocationsResponse? = null,

    @SerialName("gate")
    val gate: String? = null,

    @SerialName("expectedTimeGateOpen")
    val gateOpeningTime: String? = null,

    @SerialName("expectedTimeBoarding")
    val boardingTime: String? = null,

    @SerialName("actualOffBlockTime")
    val actualDepartureTime: String? = null,

    @SerialName("lastUpdatedAt")
    val lastUpdated: String? = null,
)
