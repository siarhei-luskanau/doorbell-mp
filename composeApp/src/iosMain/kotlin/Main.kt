import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import siarhei.luskanau.doorbell.mp.app.KoinAppComposable

fun mainViewController(): UIViewController = ComposeUIViewController(
    configure = { enforceStrictPlistSanityCheck = false }
) {
    KoinAppComposable()
}
