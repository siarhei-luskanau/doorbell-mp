import androidx.compose.ui.window.ComposeUIViewController
import org.company.app.App
import platform.UIKit.UIViewController

fun mainViewController(): UIViewController = ComposeUIViewController { App() }
