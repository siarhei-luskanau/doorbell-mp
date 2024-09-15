package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.compose.runtime.Composable
import org.koin.core.annotation.Single

@Single
internal class PermissionsInitializerEmpty : PermissionsInitializer {
    @Composable override fun initPermissionsController() = Unit
    override fun getPermissionsController(): Any = Any()
}
