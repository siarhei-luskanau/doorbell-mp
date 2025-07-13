package siarhei.luskanau.doorbell.mp.app.di

import org.koin.core.module.Module
import org.koin.ksp.generated.module
import siarhei.luskanau.doorbell.mp.core.common.CoreCommonModule
import siarhei.luskanau.doorbell.mp.core.firebase.CoreFirebaseModule
import siarhei.luskanau.doorbell.mp.ui.auth.UiAuthModule
import siarhei.luskanau.doorbell.mp.ui.doorbelllist.UiDoorbellListModule
import siarhei.luskanau.doorbell.mp.ui.imagedetails.UiImageDetailsModule
import siarhei.luskanau.doorbell.mp.ui.imagelist.UiImageListModule
import siarhei.luskanau.doorbell.mp.ui.permissions.UiPermissionsModule
import siarhei.luskanau.doorbell.mp.ui.splash.UiSplashModule

fun allModules(appModule: Module): List<Module> = listOf(
    appModule,
    appPlatformModule,
    CoreCommonModule().module,
    CoreFirebaseModule().module,
    UiAuthModule().module,
    UiDoorbellListModule().module,
    UiImageDetailsModule().module,
    UiImageListModule().module,
    UiPermissionsModule().module,
    UiSplashModule().module
)

expect val appPlatformModule: Module
