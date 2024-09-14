package siarhei.luskanau.doorbell.mp.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun AuthComposable(viewModel: AuthViewModel) {
    Column(
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Auth",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
internal fun AuthComposablePreview() {
    AppTheme { AuthComposable(authViewModel()) }
}

internal fun authViewModel(): AuthViewModel = object : AuthViewModel() {
}
