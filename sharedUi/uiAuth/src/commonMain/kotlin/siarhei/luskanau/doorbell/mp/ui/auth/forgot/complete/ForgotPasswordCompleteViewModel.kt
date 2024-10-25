package siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete

import androidx.lifecycle.ViewModel

abstract class ForgotPasswordCompleteViewModel : ViewModel() {
    abstract val viewState: ForgotPasswordCompleteViewState
    abstract fun onContinueClicked()
}
