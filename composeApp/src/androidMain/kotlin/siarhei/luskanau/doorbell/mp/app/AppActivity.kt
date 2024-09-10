package siarhei.luskanau.doorbell.mp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.core.Koin
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

class AppActivity : ComponentActivity() {

    var koin: Koin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppComposable(koin = requireNotNull(koin))
        }
    }
}
