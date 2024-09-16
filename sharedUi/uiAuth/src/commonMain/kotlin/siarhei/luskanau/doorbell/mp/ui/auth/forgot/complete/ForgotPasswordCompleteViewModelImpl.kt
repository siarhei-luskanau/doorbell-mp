package siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigation

@Factory
internal class ForgotPasswordCompleteViewModelImpl(
    @InjectedParam private val authNavigation: AuthNavigation
) : ForgotPasswordCompleteViewModel() {
    override fun onContinueClicked() {
        authNavigation.openLoginScreen()
    }
}
