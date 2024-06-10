import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.initKoin
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Multiplatform App") {
        val koin = initKoin(
            module {}
        ).koin
        AppComposable(koin = koin)
    }
}
