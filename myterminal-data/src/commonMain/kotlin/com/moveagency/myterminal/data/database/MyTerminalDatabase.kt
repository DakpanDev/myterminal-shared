package com.moveagency.myterminal.data.database

import androidx.room.*
import com.moveagency.myterminal.data.database.dao.DestinationsDao
import com.moveagency.myterminal.data.database.dao.FlightsDao
import com.moveagency.myterminal.data.database.entity.DestinationEntity
import com.moveagency.myterminal.data.database.entity.FlightEntity

@Database(
    entities = [
        FlightEntity::class,
        DestinationEntity::class,
    ],
    version = 1,
)
@ConstructedBy(DatabaseFactory::class)
abstract class MyTerminalDatabase : RoomDatabase() {

    abstract fun flightsDao(): FlightsDao

    abstract fun destinationsDao(): DestinationsDao

    companion object {

        val DatabaseName = "MyTerminalDatabase"
    }
}
