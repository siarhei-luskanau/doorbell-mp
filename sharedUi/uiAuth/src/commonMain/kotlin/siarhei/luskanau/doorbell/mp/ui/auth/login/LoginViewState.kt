package siarhei.luskanau.doorbell.mp.ui.auth.login

import kotlinx.coroutines.flow.StateFlow

data class LoginViewState(
    val isLoadingFlow: StateFlow<Boolean>,
    val usernameFlow: StateFlow<String>,
    val passwordFlow: StateFlow<String>,
    val errorFlow: StateFlow<Throwable?>
)
