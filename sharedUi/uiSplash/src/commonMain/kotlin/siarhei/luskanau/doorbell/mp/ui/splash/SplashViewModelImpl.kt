package siarhei.luskanau.doorbell.mp.ui.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class SplashViewModelImpl(
    @InjectedParam private val splashNavigationCallback: SplashNavigationCallback
) : SplashViewModel() {

    override fun onSplashComplete() {
        viewModelScope.launch {
            delay(DELAY_SPLASH)
            splashNavigationCallback.onSplashScreenCompleted()
        }
    }

    companion object {
        private const val DELAY_SPLASH = 1_000L
    }
}
