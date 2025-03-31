package siarhei.luskanau.doorbell.mp.app

import androidx.compose.runtime.Composable
import org.koin.compose.KoinMultiplatformApplication
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.allModules
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

@Composable
fun KoinAppComposable() = KoinMultiplatformApplication(
    config = KoinConfiguration {
        modules(
            *allModules(
                appModule = module {}
            ).toTypedArray()
        )
    }
) {
    AppComposable()
}
