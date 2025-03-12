package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightStateResponse(

    @SerialName("flightStates")
    val flightStates: List<String>? = null,
)
