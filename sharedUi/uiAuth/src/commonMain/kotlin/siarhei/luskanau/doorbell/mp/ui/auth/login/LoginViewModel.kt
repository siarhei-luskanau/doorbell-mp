package siarhei.luskanau.doorbell.mp.ui.auth.login

import androidx.lifecycle.ViewModel

abstract class LoginViewModel : ViewModel() {
    abstract val viewState: LoginViewState
    abstract fun onUsernameChange(text: String)
    abstract fun onPasswordChange(text: String)
    abstract fun onLoginClicked()
    abstract fun onRegisterClicked()
    abstract fun onForgotPasswordClicked()
}
