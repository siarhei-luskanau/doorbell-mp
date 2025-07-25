package siarhei.luskanau.doorbell.mp.navigation

import androidx.navigation.NavHostController
import siarhei.luskanau.doorbell.mp.ui.auth.AuthLogin
import siarhei.luskanau.doorbell.mp.ui.auth.AuthNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.doorbelllist.DoorbellListNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.imagedetails.ImageDetailsNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.imagelist.ImageListNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsNavigationCallback
import siarhei.luskanau.doorbell.mp.ui.splash.SplashNavigationCallback

class AppNavigation(private val navHostController: NavHostController) :
    AuthNavigationCallback,
    DoorbellListNavigationCallback,
    ImageDetailsNavigationCallback,
    ImageListNavigationCallback,
    PermissionsNavigationCallback,
    SplashNavigationCallback {

    fun goBack(): Boolean = navHostController.popBackStack()

    override fun onAuthGraphCompleted() = navHostController.navigate(
        AppRoutes.Permissions
    ) {
        launchSingleTop = true
        popUpTo<AuthLogin> { inclusive = true }
    }

    override fun onPermissionScreenCompleted() = navHostController.navigate(
        AppRoutes.DoorbellList
    ) {
        launchSingleTop = true
        popUpTo<AppRoutes.Permissions> { inclusive = true }
    }

    override fun onDoorbellSelected(doorbellId: String) = navHostController.navigate(
        AppRoutes.ImageList(doorbellId)
    ) {
        launchSingleTop = true
        popUpTo<AppRoutes.DoorbellList> { inclusive = false }
    }

    override fun onImageSelected(imageId: String) = navHostController.navigate(
        AppRoutes.ImageDetails(imageId)
    ) {
        launchSingleTop = true
        popUpTo<AppRoutes.ImageList> { inclusive = false }
    }

    override fun onSplashScreenCompleted() = navHostController.navigate(
        AuthLogin(username = null)
    ) {
        launchSingleTop = true
        popUpTo<AppRoutes.Splash> { inclusive = true }
    }
}
