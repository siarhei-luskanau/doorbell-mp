package siarhei.luskanau.doorbell.mp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.doorbell.mp.ui.auth.AuthComposable
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsComposable
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsInitializer
import siarhei.luskanau.doorbell.mp.ui.splash.SplashComposable

@Composable
fun AppComposable(koin: Koin) {
    AppTheme {
        val permissionInitializer: PermissionsInitializer = koin.get()
        permissionInitializer.initPermissionsController()
        val navHostController: NavHostController = rememberNavController()
        val appNavigation = AppNavigation(navHostController = navHostController)
        NavHost(
            navController = navHostController,
            startDestination = AppRoutes.Splash
        ) {
            composable<AppRoutes.Splash> {
                SplashComposable(
                    viewModel = viewModel { koin.get { parametersOf(appNavigation) } }
                )
            }
            composable<AppRoutes.Permissions> {
                PermissionsComposable(
                    viewModel = viewModel {
                        koin.get {
                            parametersOf(
                                permissionInitializer.getPermissionsController(),
                                appNavigation
                            )
                        }
                    }
                )
            }
            composable<AppRoutes.Auth> {
                AuthComposable(
                    viewModel = viewModel {
                        koin.get { parametersOf(appNavigation) }
                    }
                )
            }
            composable<AppRoutes.DoorbellList> {
                Text(
                    text = "DoorbellList",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

internal sealed interface AppRoutes {
    @Serializable data object Splash : AppRoutes

    @Serializable data object Permissions : AppRoutes

    @Serializable data object Auth : AppRoutes

    @Serializable data object DoorbellList : AppRoutes
}
