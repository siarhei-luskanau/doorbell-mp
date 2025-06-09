package siarhei.luskanau.doorbell.mp.ui.auth.forgot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_continue
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_forgot_password_title
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_username

@Composable
internal fun ForgotPasswordScreen(viewModelProvider: () -> ForgotPasswordViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    ForgotPasswordComposable(viewModel = viewModel)
}

@Composable
internal fun ForgotPasswordComposable(viewModel: ForgotPasswordViewModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(UiCommonRes.string.ui_auth_forgot_password_title),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = viewModel.viewState.usernameFlow.collectAsState().value,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_username)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(
            onClick = { viewModel.onContinueClicked() },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = stringResource(UiCommonRes.string.ui_auth_continue),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
internal fun ForgotPasswordComposableLoadingPreview() {
    AppTheme {
        ForgotPasswordComposable(
            viewModel = forgotPasswordViewModel(isLoadingFlow = MutableStateFlow(true))
        )
    }
}

@Preview
@Composable
internal fun ForgotPasswordComposableNormalPreview() {
    AppTheme {
        ForgotPasswordComposable(viewModel = forgotPasswordViewModel())
    }
}

@Preview
@Composable
internal fun ForgotPasswordComposableErrorPreview() {
    AppTheme {
        ForgotPasswordComposable(
            viewModel = forgotPasswordViewModel(
                errorFlow = MutableStateFlow(Error("Something went wrong"))
            )
        )
    }
}

private fun forgotPasswordViewModel(
    isLoadingFlow: StateFlow<Boolean> = MutableStateFlow(false),
    usernameFlow: StateFlow<String> = MutableStateFlow("test@test.test"),
    errorFlow: StateFlow<Throwable?> = MutableStateFlow(null)
) = object : ForgotPasswordViewModel() {
    override val viewState =
        ForgotPasswordViewState(
            isLoadingFlow = isLoadingFlow,
            usernameFlow = usernameFlow,
            errorFlow = errorFlow
        )
    override fun onUsernameChange(text: String) = Unit
    override fun onContinueClicked() = Unit
}
