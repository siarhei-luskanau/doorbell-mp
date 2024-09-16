package siarhei.luskanau.doorbell.mp.ui.auth.register

import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.core.firebase.FirebaseProvider
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigationCallback

@Factory
internal class RegisterViewModelImpl(
    @InjectedParam private val authNavigationCallback: AuthNavigationCallback,
    @InjectedParam private val username: String,
    private val firebaseProvider: FirebaseProvider
) : RegisterViewModel() {

    private val isLoadingFlow = MutableStateFlow(false)
    private val usernameFlow = MutableStateFlow(username)
    private val password1Flow = MutableStateFlow("")
    private val password2Flow = MutableStateFlow("")
    private val errorFlow = MutableStateFlow<Throwable?>(null)

    override val viewState = RegisterViewState(
        isLoadingFlow = isLoadingFlow,
        usernameFlow = usernameFlow,
        password1Flow = password1Flow,
        password2Flow = password2Flow,
        errorFlow = errorFlow
    )

    override fun onUsernameChange(text: String) {
        viewModelScope.launch {
            usernameFlow.emit(text)
        }
    }

    override fun onPassword1Change(text: String) {
        viewModelScope.launch {
            password1Flow.emit(text)
        }
    }

    override fun onPassword2Change(text: String) {
        viewModelScope.launch {
            password2Flow.emit(text)
        }
    }

    override fun onContinueClicked() {
        viewModelScope.launch {
            if (password1Flow.value != password2Flow.value) {
                errorFlow.emit(Throwable("Passwords do not match"))
                return@launch
            }
            val auth = firebaseProvider.getFirebaseAuth()
            if (auth.currentUser == null) {
                try {
                    auth.createUserWithEmailAndPassword(
                        email = usernameFlow.value,
                        password = password1Flow.value
                    )
                    authNavigationCallback.onAuthGraphCompleted()
                } catch (error: Throwable) {
                    if (error is FirebaseAuthUserCollisionException) {
                        try {
                            auth.signInWithEmailAndPassword(
                                email = usernameFlow.value,
                                password = password1Flow.value
                            )
                            authNavigationCallback.onAuthGraphCompleted()
                        } catch (error: Throwable) {
                            errorFlow.emit(error)
                        }
                    } else {
                        errorFlow.emit(error)
                    }
                }
            } else {
                authNavigationCallback.onAuthGraphCompleted()
            }
        }
    }
}
