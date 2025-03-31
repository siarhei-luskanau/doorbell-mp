package siarhei.luskanau.doorbell.mp.app.di

import org.koin.core.module.Module
import org.koin.ksp.generated.module
import siarhei.luskanau.doorbell.mp.core.common.CoreCommonModule
import siarhei.luskanau.doorbell.mp.core.firebase.CoreFirebaseModule
import siarhei.luskanau.doorbell.mp.ui.auth.UiAuthModule
import siarhei.luskanau.doorbell.mp.ui.permissions.UiPermissionsModule
import siarhei.luskanau.doorbell.mp.ui.splash.UiSplashModule

fun allModules(appModule: Module): List<Module> = listOf(
    appModule,
    appPlatformModule,
    CoreCommonModule().module,
    CoreFirebaseModule().module,
    UiAuthModule().module,
    UiPermissionsModule().module,
    UiSplashModule().module
)

expect val appPlatformModule: Module
