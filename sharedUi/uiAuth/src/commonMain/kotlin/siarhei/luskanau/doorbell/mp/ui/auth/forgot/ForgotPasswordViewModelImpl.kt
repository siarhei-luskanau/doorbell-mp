package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Provided
import siarhei.luskanau.doorbell.mp.core.firebase.FirebaseProvider
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigation

@Factory
internal class ForgotPasswordViewModelImpl(
    @InjectedParam private val authNavigation: AuthNavigation,
    @InjectedParam private val username: String,
    @Provided private val firebaseProvider: FirebaseProvider
) : ForgotPasswordViewModel() {

    private val isLoadingFlow = MutableStateFlow(false)
    private val usernameFlow = MutableStateFlow(username)
    private val errorFlow = MutableStateFlow<Throwable?>(null)

    override val viewState = ForgotPasswordViewState(
        isLoadingFlow = isLoadingFlow,
        usernameFlow = usernameFlow,
        errorFlow = errorFlow
    )

    override fun onUsernameChange(text: String) {
        viewModelScope.launch {
            usernameFlow.emit(text)
        }
    }

    override fun onContinueClicked() {
        viewModelScope.launch {
            try {
                val username = usernameFlow.value
                val auth = firebaseProvider.getFirebaseAuth()
                auth.sendPasswordResetEmail(email = username)
                authNavigation.openForgotPasswordCompleteScreen(username = username)
            } catch (error: Throwable) {
                errorFlow.emit(error)
            }
        }
    }
}
