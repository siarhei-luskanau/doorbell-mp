package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class AuthViewModel : ViewModel() {
    abstract val viewState: StateFlow<AuthViewState>
    abstract fun onLaunched()
}
