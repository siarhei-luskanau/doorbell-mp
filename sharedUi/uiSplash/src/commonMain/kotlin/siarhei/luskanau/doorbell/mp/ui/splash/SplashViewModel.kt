package siarhei.luskanau.doorbell.mp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val splashTermFlow by lazy { MutableStateFlow(SplashVewState(null)) }

    fun getSplashVewStateFlow(): Flow<SplashVewState> = splashTermFlow

    suspend fun onScreenLoaded() {
        viewModelScope.launch {
            splashTermFlow.emit(SplashVewState(null))
        }
    }
    fun onClick() {
        viewModelScope.launch {
            splashTermFlow.emit(SplashVewState(splashTermFlow.value.hasLogin != true))
        }
    }

    private suspend fun emitAuthState() {
        splashTermFlow.emit(SplashVewState(false))
    }
}
