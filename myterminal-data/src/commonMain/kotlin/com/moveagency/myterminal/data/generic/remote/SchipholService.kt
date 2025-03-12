package com.moveagency.myterminal.data.generic.remote

import com.moveagency.myterminal.data.BuildConfig
import com.moveagency.myterminal.data.generic.remote.response.DestinationResponse
import com.moveagency.myterminal.data.generic.remote.response.FlightListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.appendPathSegments
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

@Factory
class SchipholService(
    private val client: HttpClient,
) {

    suspend fun retrieveFlights(page: Int, date: LocalDate): FlightListResponse {
        val response = makeRequest(
            url = FlightsUrl,
            queryParams = mapOf(
                ParamScheduleDate to date.toString(),
                ParamPage to page,
                ParamIncludeDelays to false,
                ParamFlightDirection to "D",
                ParamSort to "+scheduleTime",
            ),
        )
        return response.body()
    }

    suspend fun retrieveDestination(iata: String): DestinationResponse {
        val response = makeRequest(
            url = DestinationsUrl,
            pathSegments = listOf(iata),
        )
        return response.body()
    }

    private suspend fun makeRequest(
        url: String,
        pathSegments: List<String> = emptyList(),
        queryParams: Map<String, Any> = emptyMap(),
    ) = client.get(url) {
        headers {
            append(HttpHeaders.Accept, "application/json")
            append(HeaderResourceVersion, "v4")
            append(HeaderAppId, BuildConfig.AppId)
            append(HeaderAppKey, BuildConfig.AppKey)
        }
        url {
            appendPathSegments(pathSegments)
            for (entry in queryParams.entries) {
                parameters.append(entry.key, entry.value.toString())
            }
        }
    }

    companion object {

        private const val BaseUrl = "https://api.schiphol.nl"
        private const val FlightsEndpoint = "/public-flights/flights"
        private const val DestinationsEndpoint = "/public-flights/destinations"
        private const val FlightsUrl = "$BaseUrl$FlightsEndpoint"
        private const val DestinationsUrl = "$BaseUrl$DestinationsEndpoint"

        private const val HeaderResourceVersion = "ResourceVersion"
        private const val HeaderAppId = "app_id"
        private const val HeaderAppKey = "app_key"

        private const val ParamScheduleDate = "scheduleDate"
        private const val ParamPage = "page"
        private const val ParamIncludeDelays = "includedelays"
        private const val ParamFlightDirection = "flightDirection"
        private const val ParamSort = "sort"
    }
}
