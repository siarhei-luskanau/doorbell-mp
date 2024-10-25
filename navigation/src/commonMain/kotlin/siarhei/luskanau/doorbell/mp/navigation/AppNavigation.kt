package siarhei.luskanau.doorbell.mp.navigation

import androidx.navigation.NavHostController
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.splash.SplashNavigationCallback

class AppNavigation(private val navHostController: NavHostController) :
    AuthNavigationCallback,
    PermissionsNavigationCallback,
    SplashNavigationCallback {

    fun goBack(): Boolean = navHostController.popBackStack()

    override fun onPermissionScreenCompleted() = navHostController.navigate(AppRoutes.Auth) {
        launchSingleTop = true
        popUpTo<AppRoutes.Permissions> {
            inclusive = true
        }
    }

    override fun onSplashScreenCompleted() = navHostController.navigate(AppRoutes.Permissions) {
        launchSingleTop = true
        popUpTo<AppRoutes.Splash> {
            inclusive = true
        }
    }
}
