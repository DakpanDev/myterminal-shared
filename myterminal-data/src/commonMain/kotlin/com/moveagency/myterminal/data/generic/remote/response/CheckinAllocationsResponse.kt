package com.moveagency.myterminal.data.generic.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckinAllocationsResponse(

    @SerialName("checkinAllocations")
    val allocations: List<CheckinAllocationResponse>? = null,
)

@Serializable
data class CheckinAllocationResponse(

    @SerialName("endTime")
    val endTime: String? = null,

    @SerialName("rows")
    val rows: RowsResponse? = null,
)

@Serializable
data class RowsResponse(

    @SerialName("rows")
    val rows: List<RowResponse>? = null,
)

@Serializable
data class RowResponse(

    @SerialName("position")
    val position: String? = null,
)
