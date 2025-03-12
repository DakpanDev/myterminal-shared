package com.moveagency.myterminal.data.generic.datastore

import com.moveagency.myterminal.data.database.MyTerminalDatabase
import com.moveagency.myterminal.data.database.entity.DestinationEntity
import org.koin.core.annotation.Factory

@Factory
class DestinationsDataStoreImpl(
    private val database: MyTerminalDatabase,
) : DestinationsDataStore {

    override fun getDestinationValue(iata: String): String? {
        return database.destinationsDao().findByIata(iata)?.value
    }

    override fun storeDestination(iata: String, value: String) {
        val entity = DestinationEntity(iata, value)
        database.destinationsDao().insertDestination(entity)
    }
}
