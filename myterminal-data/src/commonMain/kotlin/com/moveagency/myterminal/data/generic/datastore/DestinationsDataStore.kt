package com.moveagency.myterminal.data.generic.datastore

interface DestinationsDataStore {

    suspend fun getDestinationValue(iata: String): String?

    suspend fun storeDestination(iata: String, value: String)
}
