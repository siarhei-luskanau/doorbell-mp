import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import siarhei.luskanau.doorbell.mp.app.KoinAppComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Multiplatform App") {
        KoinAppComposable()
    }
}
