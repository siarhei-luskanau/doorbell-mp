package siarhei.luskanau.doorbell.mp.ui.auth

import dev.gitlive.firebase.auth.FirebaseUser

sealed interface AuthViewState {
    data object Loading : AuthViewState
    data class Success(var firebaseUser: FirebaseUser?) : AuthViewState
    data class Error(val error: Throwable) : AuthViewState
}
