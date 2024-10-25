package siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigation

@Factory
internal class ForgotPasswordCompleteViewModelImpl(
    @InjectedParam private val authNavigation: AuthNavigation,
    @InjectedParam private val username: String
) : ForgotPasswordCompleteViewModel() {
    override val viewState = ForgotPasswordCompleteViewState(username = username)
    override fun onContinueClicked() {
        authNavigation.goForgotPasswordCompleteToLoginScreen(username = username)
    }
}
