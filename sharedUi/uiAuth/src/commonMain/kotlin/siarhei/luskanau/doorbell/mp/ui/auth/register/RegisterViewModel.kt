package siarhei.luskanau.doorbell.mp.ui.auth.register

import androidx.lifecycle.ViewModel

abstract class RegisterViewModel : ViewModel() {
    abstract val viewState: RegisterViewState
    abstract fun onUsernameChange(text: String)
    abstract fun onPassword1Change(text: String)
    abstract fun onPassword2Change(text: String)
    abstract fun onContinueClicked()
}
