package siarhei.luskanau.doorbell.mp.app

import org.koin.core.annotation.KoinApplication
import siarhei.luskanau.doorbell.mp.core.common.CoreCommonModule
import siarhei.luskanau.doorbell.mp.core.firebase.CoreFirebaseModule
import siarhei.luskanau.doorbell.mp.ui.auth.UiAuthModule
import siarhei.luskanau.doorbell.mp.ui.doorbelllist.UiDoorbellListModule
import siarhei.luskanau.doorbell.mp.ui.imagedetails.UiImageDetailsModule
import siarhei.luskanau.doorbell.mp.ui.imagelist.UiImageListModule
import siarhei.luskanau.doorbell.mp.ui.permissions.UiPermissionsModule
import siarhei.luskanau.doorbell.mp.ui.splash.UiSplashModule

@KoinApplication(
    modules = [
        CoreCommonModule::class,
        CoreFirebaseModule::class,
        DiCommonModule::class,
        UiAuthModule::class,
        UiDoorbellListModule::class,
        UiImageDetailsModule::class,
        UiImageListModule::class,
        UiPermissionsModule::class,
        UiSplashModule::class
    ]
)
internal class DiKoinApplication
