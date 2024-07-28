package siarhei.luskanau.doorbell.mp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.Koin
import siarhei.luskanau.doorbell.mp.ui.splash.SplashComposable
import siarhei.luskanau.doorbell.mp.ui.splash.SplashViewModel

@Composable
fun AppComposable(koin: Koin) {
    SplashComposable(
        viewModel = viewModel { SplashViewModel() }
    )
}
