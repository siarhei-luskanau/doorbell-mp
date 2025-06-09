package siarhei.luskanau.doorbell.mp.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.general_error_message
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_confirm_password
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_continue
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_create_password
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_show_password
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_username

@Composable
internal fun RegisterScreen(viewModelProvider: () -> RegisterViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    RegisterComposable(viewModel = viewModel)
}

@Composable
internal fun RegisterComposable(viewModel: RegisterViewModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.viewState.usernameFlow.collectAsState().value,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_username)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        val showPassword1 = remember { mutableStateOf(false) }
        OutlinedTextField(
            value = viewModel.viewState.password1Flow.collectAsState().value,
            visualTransformation = if (!showPassword1.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            onValueChange = { viewModel.onPassword1Change(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_create_password)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            trailingIcon = {
                IconButton(onClick = { showPassword1.value = !showPassword1.value }) {
                    Icon(
                        imageVector = if (showPassword1.value) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = stringResource(
                            UiCommonRes.string.ui_auth_show_password
                        )
                    )
                }
            }
        )
        val showPassword2 = remember { mutableStateOf(false) }
        OutlinedTextField(
            value = viewModel.viewState.password2Flow.collectAsState().value,
            visualTransformation = if (!showPassword2.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            onValueChange = { viewModel.onPassword2Change(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_confirm_password)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            trailingIcon = {
                IconButton(onClick = { showPassword2.value = !showPassword2.value }) {
                    Icon(
                        imageVector = if (showPassword2.value) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = stringResource(
                            UiCommonRes.string.ui_auth_show_password
                        )
                    )
                }
            }
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
        viewModel.viewState.errorFlow.collectAsState().value?.let { error ->
            Text(
                text =
                stringResource(UiCommonRes.string.general_error_message) +
                    "\n${error.message.orEmpty()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (viewModel.viewState.isLoadingFlow.collectAsState().value) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@Preview
@Composable
internal fun RegisterComposablePreview() {
    AppTheme {
        RegisterComposable(
            registerViewModel(
                RegisterViewState(
                    isLoadingFlow = MutableStateFlow(true),
                    usernameFlow = MutableStateFlow(""),
                    password1Flow = MutableStateFlow(""),
                    password2Flow = MutableStateFlow(""),
                    errorFlow = MutableStateFlow(Error("Something went wrong"))
                )
            )
        )
    }
}

internal fun registerViewModel(authViewState: RegisterViewState): RegisterViewModel =
    object : RegisterViewModel() {
        override val viewState: RegisterViewState = authViewState
        override fun onUsernameChange(text: String) = Unit
        override fun onPassword1Change(text: String) = Unit
        override fun onPassword2Change(text: String) = Unit
        override fun onContinueClicked() = Unit
    }
