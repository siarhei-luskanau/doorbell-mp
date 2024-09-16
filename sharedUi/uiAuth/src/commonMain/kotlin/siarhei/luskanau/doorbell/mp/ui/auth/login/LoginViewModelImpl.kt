package siarhei.luskanau.doorbell.mp.ui.auth.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.core.firebase.FirebaseProvider
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigation
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigationCallback

@Factory
internal class LoginViewModelImpl(
    @InjectedParam private val authNavigation: AuthNavigation,
    @InjectedParam private val authNavigationCallback: AuthNavigationCallback,
    private val firebaseProvider: FirebaseProvider
) : LoginViewModel() {

    private val isLoadingFlow = MutableStateFlow(false)
    private val loginFlow = MutableStateFlow("")
    private val passwordFlow = MutableStateFlow("")
    private val errorFlow = MutableStateFlow<Throwable?>(null)

    override val viewState = LoginViewState(
        isLoadingFlow = isLoadingFlow,
        loginFlow = loginFlow,
        passwordFlow = passwordFlow,
        errorFlow = errorFlow
    )

    override fun onLoginClicked() {
        viewModelScope.launch {
            val auth = firebaseProvider.getFirebaseAuth()
            if (auth.currentUser == null) {
                try {
                    auth.signInWithEmailAndPassword(
                        email = loginFlow.value,
                        password = passwordFlow.value
                    )
                    authNavigationCallback.onAuthGraphCompleted()
                } catch (error: Throwable) {
                    errorFlow.emit(error)
                }
            } else {
                authNavigationCallback.onAuthGraphCompleted()
            }
        }
    }

    override fun onLoginChange(text: String) {
        viewModelScope.launch {
            loginFlow.emit(text)
        }
    }

    override fun onPasswordChange(text: String) {
        viewModelScope.launch {
            passwordFlow.emit(text)
        }
    }

    override fun onRegisterClicked() {
        authNavigation.openRegisterScreen()
    }

    override fun onForgotPasswordClicked() {
        authNavigation.openForgotPasswordScreen()
    }
}
