package com.moveagency.myterminal.data.database.dao

import androidx.room.*
import com.moveagency.myterminal.data.database.entity.DestinationEntity

@Dao
interface DestinationsDao {

    @Query("SELECT * FROM destination WHERE iata = :iata")
    suspend fun findByIata(iata: String): DestinationEntity?

    @Insert
    suspend fun insertDestination(destination: DestinationEntity)
}
