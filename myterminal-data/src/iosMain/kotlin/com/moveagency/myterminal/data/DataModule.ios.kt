package com.moveagency.myterminal.data

import androidx.room.Room
import androidx.sqlite.driver.NativeSQLiteDriver
import com.moveagency.myterminal.data.database.Converters
import com.moveagency.myterminal.data.database.MyTerminalDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import org.koin.core.annotation.*
import platform.Foundation.*

@Module
@ComponentScan("com.moveagency.myterminal.data")
actual class DataModule {

    @Single
    fun httpClient() = HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Factory
    fun myTerminalDatabase(converters: Converters) = Room.databaseBuilder<MyTerminalDatabase>(
        name = documentDirectory() + "/my_room.db",
    )
        .addTypeConverter(converters)
        .setDriver(NativeSQLiteDriver())
        .build()

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}
