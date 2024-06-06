package siarhei.luskanau.doorbell.mp.navigation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.Koin
import siarhei.luskanau.doorbell.mp.ui.splash.SplashComposable

@Preview
@Composable
fun AppComposable(koin: Koin) {
    SplashComposable()
}
