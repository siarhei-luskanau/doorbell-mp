package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.ForgotPasswordComposableErrorPreview
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.ForgotPasswordComposableLoadingPreview
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.ForgotPasswordComposableNormalPreview
import siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete.ForgotPasswordCompleteComposablePreview
import siarhei.luskanau.doorbell.mp.ui.auth.login.LoginComposablePreview
import siarhei.luskanau.doorbell.mp.ui.auth.register.RegisterComposablePreview

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun LoginScreen() = LoginComposablePreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun RegisterScreen() = RegisterComposablePreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun ForgotPasswordLoadingScreen() = ForgotPasswordComposableLoadingPreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun ForgotPasswordNormalScreen() = ForgotPasswordComposableNormalPreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun ForgotPasswordErrorScreen() = ForgotPasswordComposableErrorPreview()

@PreviewTest
@Preview(showBackground = true)
@Composable
internal fun ForgotPasswordCompleteScreen() = ForgotPasswordCompleteComposablePreview()
