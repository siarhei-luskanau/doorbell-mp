package siarhei.luskanau.doorbell.mp.ui.auth.register

import kotlinx.coroutines.flow.StateFlow

data class RegisterViewState(
    val isLoadingFlow: StateFlow<Boolean>,
    val usernameFlow: StateFlow<String>,
    val password1Flow: StateFlow<String>,
    val password2Flow: StateFlow<String>,
    val errorFlow: StateFlow<Throwable?>
)
