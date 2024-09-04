package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.compose.runtime.Composable

interface PermissionsInitializer {
    @Composable fun initPermissionsController()
    fun getPermissionsController(): Any
}
