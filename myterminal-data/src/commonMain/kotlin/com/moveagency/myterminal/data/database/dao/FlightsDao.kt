package com.moveagency.myterminal.data.database.dao

import androidx.room.*
import com.moveagency.myterminal.data.database.entity.FlightEntity

@Dao
interface FlightsDao {

    @Query("SELECT * FROM flight ORDER BY departure_datetime")
    fun getAll(): List<FlightEntity>

    @Query("SELECT * FROM flight WHERE id = :flightId")
    fun findById(flightId: String): FlightEntity?

    @Insert
    fun insertFlight(flight: FlightEntity)

    @Delete
    fun delete(flight: FlightEntity)
}
