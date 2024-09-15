package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun AuthComposable(viewModel: AuthViewModel) {
    val viewState = viewModel.viewState.collectAsState()
    Column(
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        when (val result = viewState.value) {
            AuthViewState.Loading -> CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            is AuthViewState.Success -> {
                val userInfo = result.firebaseUser?.let { user ->
                    "uid: ${user.uid}\n" +
//                        "displayName: ${user.displayName}\n" +
//                        "email: ${user.email}\n" +
//                        "isEmailVerified: ${user.isEmailVerified}\n" +
//                        "providerId: ${user.providerId}\n" +
                        "isAnonymous: ${user.isAnonymous}\n"
                } ?: "Not logged in"
                Text(
                    text = userInfo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            is AuthViewState.Error -> Text(
                text = "Something went wrong\n${result.error.message.orEmpty()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    LaunchedEffect(true) {
        viewModel.onLaunched()
    }
}

@Preview
@Composable
internal fun AuthLoadingComposablePreview() {
    AppTheme { AuthComposable(authViewModel(AuthViewState.Loading)) }
}

@Preview
@Composable
internal fun AuthSuccessComposablePreview() {
    AppTheme { AuthComposable(authViewModel(AuthViewState.Success(null))) }
}

@Preview
@Composable
internal fun AuthErrorComposablePreview() {
    AppTheme { AuthComposable(authViewModel(AuthViewState.Error(Error("Test Error")))) }
}

internal fun authViewModel(authViewState: AuthViewState): AuthViewModel = object : AuthViewModel() {
    override val viewState: StateFlow<AuthViewState> = MutableStateFlow(authViewState)
    override fun onLaunched() = Unit
}
