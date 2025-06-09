package siarhei.luskanau.doorbell.mp.ui.auth.forgot.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_continue
import siarhei.luskanau.doorbell.mp.ui.common.ui_auth_forgot_password_complete_title

@Composable
internal fun ForgotPasswordCompleteScreen(
    viewModelProvider: () -> ForgotPasswordCompleteViewModel
) {
    val viewModel = viewModel { viewModelProvider() }
    ForgotPasswordCompleteComposable(viewModel = viewModel)
}

@Composable
internal fun ForgotPasswordCompleteComposable(viewModel: ForgotPasswordCompleteViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(UiCommonRes.string.ui_auth_forgot_password_complete_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = viewModel.viewState.username,
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
            imageVector = Icons.Rounded.CheckCircle,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(96.dp))
        Button(
            onClick = { viewModel.onContinueClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
internal fun ForgotPasswordCompleteComposablePreview() {
    AppTheme {
        ForgotPasswordCompleteComposable(object : ForgotPasswordCompleteViewModel() {
            override val viewState = ForgotPasswordCompleteViewState(username = "test@test.test")
            override fun onContinueClicked() = Unit
        })
    }
}
