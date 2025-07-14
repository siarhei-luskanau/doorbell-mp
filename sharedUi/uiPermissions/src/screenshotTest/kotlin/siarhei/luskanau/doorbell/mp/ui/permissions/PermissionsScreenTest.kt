package siarhei.luskanau.doorbell.mp.ui.permissions

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun PermissionsDeniedScreen() = PermissionsComposableDeniedPreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun PermissionsGrantedScreen() = PermissionsComposableGrantedPreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun PermissionsNotGrantedScreen() = PermissionsComposableNotGrantedPreview()
