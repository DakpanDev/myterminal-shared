package com.moveagency.myterminal.shared

import com.moveagency.myterminal.data.DataModule
import com.moveagency.myterminal.domain.DomainModule
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun initKoin(platformScope: KoinApplication.() -> Unit = {}) {
    startKoin {
        platformScope()
        loadKoinModules(
            buildList {
                add(DomainModule().module)
                add(DataModule().module)
            }
        )
    }
}
