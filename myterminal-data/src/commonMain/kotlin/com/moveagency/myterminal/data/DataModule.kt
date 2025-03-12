package com.moveagency.myterminal.data

import android.content.Context
import androidx.room.Room
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
class DataModule {

    @Single
    fun httpClient() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Factory
    fun myTerminalDatabase(context: Context, converters: Converters) = Room.databaseBuilder(
        context,
        MyTerminalDatabase::class.java,
        MyTerminalDatabase.DatabaseName,
    )
        .addTypeConverter(converters)
        .build()
}
