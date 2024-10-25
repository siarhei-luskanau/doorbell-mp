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
    @InjectedParam private val username: String?,
    private val firebaseProvider: FirebaseProvider
) : LoginViewModel() {

    private val isLoadingFlow = MutableStateFlow(false)
    private val usernameFlow = MutableStateFlow(username.orEmpty())
    private val passwordFlow = MutableStateFlow("")
    private val errorFlow = MutableStateFlow<Throwable?>(null)

    override val viewState = LoginViewState(
        isLoadingFlow = isLoadingFlow,
        usernameFlow = usernameFlow,
        passwordFlow = passwordFlow,
        errorFlow = errorFlow
    )

    override fun onLoginClicked() {
        viewModelScope.launch {
            val auth = firebaseProvider.getFirebaseAuth()
            if (auth.currentUser == null) {
                try {
                    auth.signInWithEmailAndPassword(
                        email = usernameFlow.value,
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

    override fun onUsernameChange(text: String) {
        viewModelScope.launch {
            usernameFlow.emit(text)
        }
    }

    override fun onPasswordChange(text: String) {
        viewModelScope.launch {
            passwordFlow.emit(text)
        }
    }

    override fun onRegisterClicked() {
        authNavigation.openRegisterScreen(username = usernameFlow.value)
    }

    override fun onForgotPasswordClicked() {
        authNavigation.openForgotPasswordScreen(username = usernameFlow.value)
    }
}
