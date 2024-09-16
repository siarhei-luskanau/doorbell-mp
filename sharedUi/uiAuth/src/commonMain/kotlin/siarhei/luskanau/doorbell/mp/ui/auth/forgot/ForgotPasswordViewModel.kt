package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import androidx.lifecycle.ViewModel

abstract class ForgotPasswordViewModel : ViewModel() {
    abstract fun onContinueClicked()
}
