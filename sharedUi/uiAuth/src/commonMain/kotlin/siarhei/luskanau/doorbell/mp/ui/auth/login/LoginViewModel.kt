package siarhei.luskanau.doorbell.mp.ui.auth.login

import androidx.lifecycle.ViewModel

abstract class LoginViewModel : ViewModel() {
    abstract val viewState: LoginViewState
    abstract fun onLoginChange(text: String)
    abstract fun onPasswordChange(text: String)
    abstract fun onLoginClicked()
    abstract fun onRegisterClicked()
    abstract fun onForgotPasswordClicked()
}
