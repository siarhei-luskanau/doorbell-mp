package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.ForgotPasswordComposable
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete.ForgotPasswordCompleteComposable
import siarhei.luskanau.doorbell.mp.ui.auth.login.LoginComposable
import siarhei.luskanau.doorbell.mp.ui.auth.register.RegisterComposable

fun NavGraphBuilder.authGraph(
    koin: Koin,
    navHostController: NavHostController,
    authNavigationCallback: AuthNavigationCallback
) {
    val authNavigation = AuthNavigation(navHostController = navHostController)
    navigation<AuthGraph>(startDestination = AuthLogin) {
        composable<AuthForgotPassword> {
            ForgotPasswordComposable(
                viewModel = viewModel { koin.get { parametersOf(authNavigation) } }
            )
        }
        composable<AuthForgotPasswordComplete> {
            ForgotPasswordCompleteComposable(
                viewModel = viewModel { koin.get { parametersOf(authNavigation) } }
            )
        }
        composable<AuthLogin> {
            LoginComposable(
                viewModel = viewModel {
                    koin.get { parametersOf(authNavigation, authNavigationCallback) }
                }
            )
        }
        composable<AuthRegister> {
            RegisterComposable(
                viewModel = viewModel { koin.get { parametersOf(authNavigationCallback) } }
            )
        }
    }
}

@Serializable data object AuthGraph

sealed interface AuthRoutes

@Serializable data object AuthLogin : AuthRoutes

@Serializable internal data object AuthRegister : AuthRoutes

@Serializable internal data object AuthForgotPassword : AuthRoutes

@Serializable internal data object AuthForgotPasswordComplete : AuthRoutes
