package siarhei.luskanau.doorbell.mp.navigation

import androidx.navigation.NavHostController
import siarhei.luskanau.doorbell.mp.ui.permissions.PermissionsNavigationCallback

class AppNavigation(private val navHostController: NavHostController) :
    PermissionsNavigationCallback {

    fun goBack(): Boolean = navHostController.popBackStack()

    override fun onPermissionScreenCompleted() = navHostController.navigate(AppRoutes.Auth)
}
