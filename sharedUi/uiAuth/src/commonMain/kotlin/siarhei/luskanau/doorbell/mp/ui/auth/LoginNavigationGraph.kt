package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.ForgotPasswordScreen
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete.ForgotPasswordCompleteScreen
import siarhei.luskanau.doorbell.mp.ui.auth.login.LoginScreen
import siarhei.luskanau.doorbell.mp.ui.auth.register.RegisterScreen

fun NavGraphBuilder.authGraph(
    koin: Koin,
    navHostController: NavHostController,
    authNavigationCallback: AuthNavigationCallback
) {
    val authNavigation = AuthNavigation(navHostController = navHostController)
    navigation<AuthGraph>(startDestination = AuthLogin(username = null)) {
        composable<AuthForgotPassword> {
            val args: AuthForgotPassword = it.toRoute()
            ForgotPasswordScreen { koin.get { parametersOf(authNavigation, args.username) } }
        }
        composable<AuthForgotPasswordComplete> {
            val args: AuthForgotPasswordComplete = it.toRoute()
            ForgotPasswordCompleteScreen {
                koin.get { parametersOf(authNavigation, args.username) }
            }
        }
        composable<AuthLogin> {
            val args: AuthLogin = it.toRoute()
            LoginScreen {
                koin.get { parametersOf(authNavigation, authNavigationCallback, args.username) }
            }
        }
        composable<AuthRegister> {
            val args: AuthRegister = it.toRoute()
            RegisterScreen { koin.get { parametersOf(authNavigationCallback, args.username) } }
        }
    }
}

@Serializable data object AuthGraph

sealed interface AuthRoutes

@Serializable data class AuthLogin(val username: String?) : AuthRoutes

@Serializable internal data class AuthRegister(val username: String) : AuthRoutes

@Serializable internal data class AuthForgotPassword(val username: String) : AuthRoutes

@Serializable internal data class AuthForgotPasswordComplete(val username: String) : AuthRoutes
