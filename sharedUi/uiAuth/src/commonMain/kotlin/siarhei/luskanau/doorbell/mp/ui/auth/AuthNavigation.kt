package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.navigation.NavHostController

internal class AuthNavigation(private val navHostController: NavHostController) {

    fun openRegisterScreen(username: String) = navHostController.navigate(
        AuthRegister(username = username)
    )

    fun openForgotPasswordScreen(username: String) = navHostController.navigate(
        AuthForgotPassword(username = username)
    )

    fun openForgotPasswordCompleteScreen(username: String) = navHostController.navigate(
        AuthForgotPasswordComplete(username = username)
    ) {
        launchSingleTop = true
        popUpTo<AuthForgotPassword> { inclusive = true }
    }

    fun goForgotPasswordCompleteToLoginScreen(username: String) = navHostController.navigate(
        AuthLogin(username = username)
    ) {
        launchSingleTop = true
        popUpTo<AuthForgotPasswordComplete> {
            inclusive = true
        }
    }
}
