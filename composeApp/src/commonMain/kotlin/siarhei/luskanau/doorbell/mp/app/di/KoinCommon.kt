package siarhei.luskanau.doorbell.mp.app.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.ksp.generated.module
import siarhei.luskanau.doorbell.mp.core.common.CoreCommonModule
import siarhei.luskanau.doorbell.mp.ui.permissions.UiPermissionsModule

fun initKoin(appModule: Module): KoinApplication = startKoin {
    modules(
        appModule,
        appPlatformModule,
        CoreCommonModule().module,
        UiPermissionsModule().module
    )
}

expect val appPlatformModule: Module
