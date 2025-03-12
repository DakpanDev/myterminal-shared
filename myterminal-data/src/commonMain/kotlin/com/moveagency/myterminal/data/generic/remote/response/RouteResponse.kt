package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RouteResponse(

    @SerialName("destinations")
    val destinations: List<String>? = null,
)
