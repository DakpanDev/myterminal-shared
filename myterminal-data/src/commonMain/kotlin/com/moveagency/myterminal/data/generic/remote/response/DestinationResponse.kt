package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationResponse(

    @SerialName(value = "iata")
    val iata: String,

    @SerialName(value = "country")
    val country: String?,

    @SerialName(value = "city")
    val city: String?,

    @SerialName(value = "publicName")
    val publicName: PublicName?
)

@Serializable
data class PublicName(

    @SerialName("english")
    val english: String?,
)
