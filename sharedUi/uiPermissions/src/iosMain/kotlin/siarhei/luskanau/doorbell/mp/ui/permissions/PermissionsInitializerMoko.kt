package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.koin.core.annotation.Single

@Single
internal class PermissionsInitializerMoko : PermissionsInitializer {

    private var permissionsController: PermissionsController? = null

    @Composable
    override fun initPermissionsController() {
        val permissionsControllerFactory = rememberPermissionsControllerFactory()
        permissionsController = remember(permissionsControllerFactory) {
            permissionsControllerFactory.createPermissionsController()
        }
        BindEffect(requireNotNull(permissionsController))
    }

    override fun getPermissionsController(): Any = requireNotNull(permissionsController)
}
