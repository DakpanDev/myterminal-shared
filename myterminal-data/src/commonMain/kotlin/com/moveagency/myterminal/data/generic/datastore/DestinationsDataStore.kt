package com.moveagency.myterminal.data.generic.datastore

interface DestinationsDataStore {

    fun getDestinationValue(iata: String): String?

    fun storeDestination(iata: String, value: String)
}
