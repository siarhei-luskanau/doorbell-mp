package siarhei.luskanau.doorbell.mp.ui.auth.register

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigationCallback

@Factory
internal class RegisterViewModelImpl(
    @InjectedParam private val authNavigationCallback: AuthNavigationCallback
) : RegisterViewModel() {
    override fun onContinueClicked() {
        authNavigationCallback.onAuthGraphCompleted()
    }
}
