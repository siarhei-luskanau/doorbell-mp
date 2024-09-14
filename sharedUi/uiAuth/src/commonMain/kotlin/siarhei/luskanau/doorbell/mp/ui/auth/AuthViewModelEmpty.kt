package siarhei.luskanau.doorbell.mp.ui.auth

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class AuthViewModelEmpty(
    @InjectedParam private val authNavigationCallback: AuthNavigationCallback
) : AuthViewModel()
