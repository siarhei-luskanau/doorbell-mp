import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.initKoin
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

fun main() = application {
    Window(
        title = "Multiplatform App",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(350, 600)
        val koin = initKoin(module {}).koin
        AppComposable(koin = koin)
    }
}
