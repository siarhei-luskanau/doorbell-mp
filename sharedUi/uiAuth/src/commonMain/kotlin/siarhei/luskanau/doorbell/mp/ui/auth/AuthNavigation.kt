package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.navigation.NavHostController

internal class AuthNavigation(private val navHostController: NavHostController) {
    fun openLoginScreen() = navHostController.navigate(AuthLogin) {
        popUpTo(AuthLogin) {
            inclusive =
                true
        }
    }
    fun openRegisterScreen() = navHostController.navigate(AuthRegister)
    fun openForgotPasswordScreen() = navHostController.navigate(AuthForgotPassword)
    fun openForgotPasswordCompleteScreen() = navHostController.navigate(AuthForgotPasswordComplete)
}
