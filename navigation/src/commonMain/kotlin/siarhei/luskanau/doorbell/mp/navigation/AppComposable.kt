package siarhei.luskanau.doorbell.mp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.doorbell.mp.ui.auth.authGraph
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.doorbelllist.DoorbellListScreen
import siarhei.luskanau.doorbell.mp.ui.imagedetails.ImageDetailsScreen
import siarhei.luskanau.doorbell.mp.ui.imagelist.ImageListScreen
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsInitializer
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsScreen
import siarhei.luskanau.doorbell.mp.ui.splash.SplashScreen

@Preview
@Composable
fun AppComposable() {
    val koin = getKoin()
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
                SplashScreen { koin.get { parametersOf(appNavigation) } }
            }
            authGraph(
                koin = koin,
                navHostController = navHostController,
                authNavigationCallback = appNavigation
            )
            composable<AppRoutes.Permissions> {
                PermissionsScreen {
                    koin.get {
                        parametersOf(
                            permissionInitializer.getPermissionsController(),
                            appNavigation
                        )
                    }
                }
            }
            composable<AppRoutes.DoorbellList> {
                DoorbellListScreen { koin.get { parametersOf(appNavigation) } }
            }
            composable<AppRoutes.ImageList> {
                val args: AppRoutes.ImageList = it.toRoute()
                ImageListScreen {
                    koin.get { parametersOf(args.doorbellId, appNavigation) }
                }
            }
            composable<AppRoutes.ImageDetails> {
                val args: AppRoutes.ImageDetails = it.toRoute()
                ImageDetailsScreen {
                    koin.get { parametersOf(args.imageId, appNavigation) }
                }
            }
        }
    }
}

internal sealed interface AppRoutes {
    @Serializable data object Splash : AppRoutes

    @Serializable data object Permissions : AppRoutes

    @Serializable data object DoorbellList : AppRoutes

    @Serializable data class ImageList(val doorbellId: String) : AppRoutes

    @Serializable data class ImageDetails(val imageId: String) : AppRoutes
}
