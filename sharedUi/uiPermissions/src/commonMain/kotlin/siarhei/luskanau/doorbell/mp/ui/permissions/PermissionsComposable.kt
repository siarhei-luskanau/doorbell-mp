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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.ic_photo_camera
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun PermissionsComposable(viewModel: PermissionsViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        val permissionsViewState by viewModel.viewState.collectAsState()
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Camera permission",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = when (permissionsViewState) {
                PermissionsViewState.NotGrantedPermissionsViewState ->
                    "The camera is important for this app. Please grant the permission."
                PermissionsViewState.GrantedPermissionsViewState ->
                    "The camera permission is granted."
                PermissionsViewState.DeniedPermissionsViewState ->
                    "The camera is important for this app. Please grant the permission in the application settings."
            },
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
                text = when (permissionsViewState) {
                    PermissionsViewState.NotGrantedPermissionsViewState,
                    PermissionsViewState.GrantedPermissionsViewState
                    -> "Request permission"
                    PermissionsViewState.DeniedPermissionsViewState -> "Open Settings"
                },
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
