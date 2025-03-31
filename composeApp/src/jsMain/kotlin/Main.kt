import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import siarhei.luskanau.doorbell.mp.app.KoinAppComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("Multiplatform App") {
            KoinAppComposable()
        }
    }
}
