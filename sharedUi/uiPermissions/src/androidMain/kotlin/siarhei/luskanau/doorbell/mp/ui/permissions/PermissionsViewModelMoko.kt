package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.camera.CAMERA
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
internal class PermissionsViewModelMoko(
    @InjectedParam private val permissionsController: PermissionsController,
    @InjectedParam private val permissionsNavigationCallback: PermissionsNavigationCallback
) : PermissionsViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            viewState.emit(
                if (permissionsController.isPermissionGranted(Permission.CAMERA)) {
                    PermissionsViewState.GrantedPermissionsViewState
                } else {
                    PermissionsViewState.NotGrantedPermissionsViewState
                }
            )
        }
    }

    override val viewState = MutableStateFlow<PermissionsViewState>(
        PermissionsViewState.NotGrantedPermissionsViewState
    )

    override fun onRequestPermissionClicked() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.CAMERA)
                viewState.emit(PermissionsViewState.GrantedPermissionsViewState)
            } catch (e: DeniedAlwaysException) {
                viewState.emit(PermissionsViewState.DeniedPermissionsViewState)
            } catch (e: DeniedException) {
                viewState.emit(PermissionsViewState.NotGrantedPermissionsViewState)
            } catch (e: RequestCanceledException) {
                viewState.emit(PermissionsViewState.NotGrantedPermissionsViewState)
            }
        }
    }

    override fun onOpenSettingsClicked() {
        viewModelScope.launch {
            permissionsController.openAppSettings()
            viewState.emit(
                if (permissionsController.getPermissionState(Permission.CAMERA) ==
                    PermissionState.Granted
                ) {
                    PermissionsViewState.GrantedPermissionsViewState
                } else {
                    PermissionsViewState.DeniedPermissionsViewState
                }
            )
        }
    }

    override fun onPermissionScreenCompleted() {
        permissionsNavigationCallback.onPermissionScreenCompleted()
    }
}
