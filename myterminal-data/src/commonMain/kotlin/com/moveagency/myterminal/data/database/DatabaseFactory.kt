package com.moveagency.myterminal.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseFactory : RoomDatabaseConstructor<MyTerminalDatabase> {
    override fun initialize(): MyTerminalDatabase
}
