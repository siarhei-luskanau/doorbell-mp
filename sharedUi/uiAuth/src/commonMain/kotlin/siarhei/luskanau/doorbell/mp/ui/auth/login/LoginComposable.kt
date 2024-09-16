package siarhei.luskanau.doorbell.mp.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun LoginComposable(viewModel: LoginViewModel) {
    Column(
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        OutlinedTextField(
            value = viewModel.viewState.loginFlow.collectAsState().value,
            onValueChange = { viewModel.onLoginChange(it) },
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        OutlinedTextField(
            value = viewModel.viewState.passwordFlow.collectAsState().value,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
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
internal fun AuthLComposablePreview() {
    AppTheme {
        LoginComposable(
            authViewModel(
                LoginViewState(
                    isLoadingFlow = MutableStateFlow(true),
                    loginFlow = MutableStateFlow(""),
                    passwordFlow = MutableStateFlow(""),
                    errorFlow = MutableStateFlow(Error("Something went wrong"))
                )
            )
        )
    }
}

internal fun authViewModel(authViewState: LoginViewState): LoginViewModel =
    object : LoginViewModel() {
        override val viewState: LoginViewState = authViewState
        override fun onLoginChange(text: String) = Unit
        override fun onPasswordChange(text: String) = Unit
        override fun onLoginClicked() = Unit
        override fun onRegisterClicked() = Unit
        override fun onForgotPasswordClicked() = Unit
    }
