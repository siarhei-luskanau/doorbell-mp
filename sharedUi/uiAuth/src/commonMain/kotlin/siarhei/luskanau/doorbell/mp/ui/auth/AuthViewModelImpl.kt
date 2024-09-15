package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.core.firebase.FirebaseProvider

@Factory
internal class AuthViewModelImpl(
    @InjectedParam private val authNavigationCallback: AuthNavigationCallback,
    private val firebaseProvider: FirebaseProvider
) : AuthViewModel() {

    override val viewState = MutableStateFlow<AuthViewState>(AuthViewState.Loading)

    override fun onLaunched() {
        viewModelScope.launch {
            val auth = firebaseProvider.getFirebaseAuth()
            var user = auth.currentUser
            if (user == null) {
                user = auth.signInAnonymously().user
            }
            viewState.emit(AuthViewState.Success(user))
        }
    }
}
