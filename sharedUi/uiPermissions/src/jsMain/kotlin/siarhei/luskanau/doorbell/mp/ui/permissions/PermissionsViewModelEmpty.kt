package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class PermissionsViewModelEmpty(
    @InjectedParam private val permissionsNavigationCallback: PermissionsNavigationCallback
) : PermissionsViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            viewState.emit(PermissionsViewState.GrantedPermissionsViewState)
        }
    }

    override val viewState = MutableStateFlow<PermissionsViewState>(
        PermissionsViewState.GrantedPermissionsViewState
    )

    override fun onRequestPermissionClicked() {
        viewModelScope.launch {
            viewState.emit(PermissionsViewState.GrantedPermissionsViewState)
        }
    }

    override fun onOpenSettingsClicked() {
        viewModelScope.launch {
            viewState.emit(PermissionsViewState.GrantedPermissionsViewState)
        }
    }

    override fun onPermissionScreenCompleted() {
        permissionsNavigationCallback.onPermissionScreenCompleted()
    }
}
