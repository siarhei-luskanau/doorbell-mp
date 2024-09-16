package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigation

@Factory
internal class ForgotPasswordViewModelImpl(
    @InjectedParam private val authNavigation: AuthNavigation
) : ForgotPasswordViewModel() {
    override fun onContinueClicked() {
        authNavigation.openForgotPasswordCompleteScreen()
    }
}
