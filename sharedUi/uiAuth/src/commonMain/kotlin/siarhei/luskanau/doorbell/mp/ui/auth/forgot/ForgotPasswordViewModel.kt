package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import androidx.lifecycle.ViewModel

abstract class ForgotPasswordViewModel : ViewModel() {
    abstract val viewState: ForgotPasswordViewState
    abstract fun onUsernameChange(text: String)
    abstract fun onContinueClicked()
}
