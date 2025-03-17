package com.moveagency.myterminal.shared

import com.moveagency.myterminal.data.DataModule
import com.moveagency.myterminal.domain.DomainModule
import org.koin.core.context.startKoin

object KoinHelper {

    fun initKoin() {
        startKoin {
            modules(
                DomainModule().module,
                DataModule().module,
            )
        }
    }
}
