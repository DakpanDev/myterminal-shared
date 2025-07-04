package com.moveagency.myterminal.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.moveagency.myterminal.data.database.Converters
import com.moveagency.myterminal.data.database.MyTerminalDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.*

@Module
@ComponentScan("com.moveagency.myterminal.data")
actual class DataModule {

    @Single
    fun httpClient() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Factory
    fun myTerminalDatabase(context: Context, converters: Converters) =
        Room.databaseBuilder<MyTerminalDatabase>(
            context = context.applicationContext,
            name = context.applicationContext.getDatabasePath("my_room.db").absolutePath,
        )
            .addTypeConverter(converters)
            .setDriver(AndroidSQLiteDriver())
            .build()
}
