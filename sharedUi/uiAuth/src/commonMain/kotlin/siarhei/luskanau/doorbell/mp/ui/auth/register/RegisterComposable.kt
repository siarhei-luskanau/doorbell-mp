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
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun RegisterComposable(viewModel: RegisterViewModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.viewState.loginFlow.collectAsState().value,
            onValueChange = { viewModel.onLoginChange(it) },
            label = { Text("Login") },
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
            label = { Text("Create Password") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            trailingIcon = {
                IconButton(onClick = { showPassword1.value = !showPassword1.value }) {
                    Icon(
                        imageVector = if (showPassword1.value) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = "Show Password"
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
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            trailingIcon = {
                IconButton(onClick = { showPassword2.value = !showPassword2.value }) {
                    Icon(
                        imageVector = if (showPassword2.value) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = "Show Password"
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
                text = "Continue",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
        viewModel.viewState.errorFlow.collectAsState().value?.let { error ->
            Text(
                text = "Something went wrong\n${error.message.orEmpty()}",
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
                    loginFlow = MutableStateFlow(""),
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
        override fun onLoginChange(text: String) = Unit
        override fun onPassword1Change(text: String) = Unit
        override fun onPassword2Change(text: String) = Unit
        override fun onContinueClicked() = Unit
    }
