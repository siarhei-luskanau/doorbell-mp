package siarhei.luskanau.doorbell.mp.ui.auth.login

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
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_password_title
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_show_password
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_username

@Composable
internal fun LoginScreen(viewModelProvider: () -> LoginViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    LoginComposable(viewModel = viewModel)
}

@Composable
internal fun LoginComposable(viewModel: LoginViewModel) {
    Column(
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        OutlinedTextField(
            value = viewModel.viewState.usernameFlow.collectAsState().value,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_username)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        val showPassword = remember { mutableStateOf(false) }
        OutlinedTextField(
            value = viewModel.viewState.passwordFlow.collectAsState().value,
            visualTransformation = if (!showPassword.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text(stringResource(UiCommonRes.string.ui_auth_password_title)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        imageVector = if (showPassword.value) {
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
            onClick = { viewModel.onLoginClicked() },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { viewModel.onRegisterClicked() },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Go to Register",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { viewModel.onForgotPasswordClicked() },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Go to ForgotPassword",
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
internal fun LoginComposablePreview() {
    AppTheme {
        LoginComposable(
            loginViewModel(
                LoginViewState(
                    isLoadingFlow = MutableStateFlow(true),
                    usernameFlow = MutableStateFlow(""),
                    passwordFlow = MutableStateFlow(""),
                    errorFlow = MutableStateFlow(Error("Something went wrong"))
                )
            )
        )
    }
}

internal fun loginViewModel(authViewState: LoginViewState): LoginViewModel =
    object : LoginViewModel() {
        override val viewState: LoginViewState = authViewState
        override fun onUsernameChange(text: String) = Unit
        override fun onPasswordChange(text: String) = Unit
        override fun onLoginClicked() = Unit
        override fun onRegisterClicked() = Unit
        override fun onForgotPasswordClicked() = Unit
    }
