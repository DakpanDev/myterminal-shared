package com.moveagency.myterminal.data.database.dao

import androidx.room.*
import com.moveagency.myterminal.data.database.entity.DestinationEntity

@Dao
interface DestinationsDao {

    @Query("SELECT * FROM destination WHERE iata = :iata")
    fun findByIata(iata: String): DestinationEntity?

    @Insert
    fun insertDestination(destination: DestinationEntity)
}
