package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightListResponse(

    @SerialName("flights")
    val flights: List<FlightDetailsResponse>? = null,
)
