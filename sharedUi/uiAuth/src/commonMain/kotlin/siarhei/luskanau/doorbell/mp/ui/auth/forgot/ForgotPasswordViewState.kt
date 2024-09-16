package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import kotlinx.coroutines.flow.StateFlow

data class ForgotPasswordViewState(
    val isLoadingFlow: StateFlow<Boolean>,
    val usernameFlow: StateFlow<String>,
    val errorFlow: StateFlow<Throwable?>
)
