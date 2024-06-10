package siarhei.luskanau.doorbell.mp.app.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(appModule: Module): KoinApplication = startKoin {
    modules(
        appModule,
        appPlatformModule
    )
}

expect val appPlatformModule: Module
