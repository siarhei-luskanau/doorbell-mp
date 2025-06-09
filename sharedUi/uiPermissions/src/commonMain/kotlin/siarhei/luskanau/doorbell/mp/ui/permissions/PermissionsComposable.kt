package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.ic_photo_camera
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_message_denied
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_message_granted
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_message_not_granted
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_open_settings
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_request_permission
import siarhei.luskanau.doorbell.mp.ui.common.ui_permissions_title

@Composable
fun PermissionsScreen(viewModelProvider: () -> PermissionsViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    PermissionsComposable(viewModel = viewModel)
}

@Composable
internal fun PermissionsComposable(viewModel: PermissionsViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        val permissionsViewState by viewModel.viewState.collectAsState()
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(UiCommonRes.string.ui_permissions_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(
                when (permissionsViewState) {
                    PermissionsViewState.NotGrantedPermissionsViewState ->
                        UiCommonRes.string.ui_permissions_message_not_granted
                    PermissionsViewState.GrantedPermissionsViewState ->
                        UiCommonRes.string.ui_permissions_message_granted
                    PermissionsViewState.DeniedPermissionsViewState ->
                        UiCommonRes.string.ui_permissions_message_denied
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f),
            painter = painterResource(UiCommonRes.drawable.ic_photo_camera),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(96.dp))
        Button(
            onClick = {
                when (permissionsViewState) {
                    PermissionsViewState.NotGrantedPermissionsViewState ->
                        viewModel.onRequestPermissionClicked()
                    PermissionsViewState.GrantedPermissionsViewState ->
                        viewModel.onPermissionScreenCompleted()
                    PermissionsViewState.DeniedPermissionsViewState ->
                        viewModel.onOpenSettingsClicked()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = stringResource(
                    when (permissionsViewState) {
                        PermissionsViewState.NotGrantedPermissionsViewState,
                        PermissionsViewState.GrantedPermissionsViewState ->
                            UiCommonRes.string.ui_permissions_request_permission
                        PermissionsViewState.DeniedPermissionsViewState ->
                            UiCommonRes.string.ui_permissions_open_settings
                    }
                ),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (permissionsViewState == PermissionsViewState.GrantedPermissionsViewState) {
            viewModel.onPermissionScreenCompleted()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.onLaunched()
    }
}

@Preview
@Composable
internal fun PermissionsComposableDeniedPreview() {
    AppTheme {
        PermissionsComposable(
            viewModel = permissionsViewModel(
                permissionsViewState = PermissionsViewState.DeniedPermissionsViewState
            )
        )
    }
}

@Preview
@Composable
internal fun PermissionsComposableGrantedPreview() {
    AppTheme {
        PermissionsComposable(
            viewModel = permissionsViewModel(
                permissionsViewState = PermissionsViewState.GrantedPermissionsViewState
            )
        )
    }
}

@Preview
@Composable
internal fun PermissionsComposableNotGrantedPreview() {
    AppTheme {
        PermissionsComposable(
            viewModel = permissionsViewModel(
                permissionsViewState = PermissionsViewState.NotGrantedPermissionsViewState
            )
        )
    }
}

internal fun permissionsViewModel(
    permissionsViewState: PermissionsViewState
): PermissionsViewModel = object : PermissionsViewModel() {
    override val viewState: StateFlow<PermissionsViewState> = MutableStateFlow(permissionsViewState)
    override fun onLaunched() = Unit
    override fun onRequestPermissionClicked() = Unit
    override fun onOpenSettingsClicked() = Unit
    override fun onPermissionScreenCompleted() = Unit
}
