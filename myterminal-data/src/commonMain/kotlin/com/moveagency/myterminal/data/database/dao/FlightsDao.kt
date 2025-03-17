package com.moveagency.myterminal.data.database.dao

import androidx.room.*
import com.moveagency.myterminal.data.database.entity.FlightEntity

@Dao
interface FlightsDao {

    @Query("SELECT * FROM flight ORDER BY departure_datetime")
    suspend fun getAll(): List<FlightEntity>

    @Query("SELECT * FROM flight WHERE id = :flightId")
    suspend fun findById(flightId: String): FlightEntity?

    @Insert
    suspend fun insertFlight(flight: FlightEntity)

    @Delete
    suspend fun delete(flight: FlightEntity)
}
