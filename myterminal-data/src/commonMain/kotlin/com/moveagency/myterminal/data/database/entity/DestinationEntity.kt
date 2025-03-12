package com.moveagency.myterminal.data.database.entity

import androidx.room.*

@Entity(tableName = "destination")
data class DestinationEntity(

    @PrimaryKey
    val iata: String,

    @ColumnInfo
    val value: String
)
