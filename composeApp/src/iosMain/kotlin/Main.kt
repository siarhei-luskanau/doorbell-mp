import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.Koin
import platform.UIKit.UIViewController
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

fun mainViewController(koin: Koin): UIViewController = ComposeUIViewController(
    configure = { enforceStrictPlistSanityCheck = false }
) {
    AppComposable(koin = koin)
}
