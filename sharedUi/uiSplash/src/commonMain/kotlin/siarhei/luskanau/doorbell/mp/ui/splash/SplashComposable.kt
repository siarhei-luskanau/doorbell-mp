package siarhei.luskanau.doorbell.mp.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.Res as UiCommonRes
import siarhei.luskanau.doorbell.mp.ui.common.ic_android
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun SplashScreen(viewModelProvider: () -> SplashViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    SplashComposable(viewModel = viewModel)
}

@Composable
internal fun SplashComposable(viewModel: SplashViewModel) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(UiCommonRes.drawable.ic_android),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
        contentDescription = null
    )
    LaunchedEffect(true) {
        viewModel.onSplashComplete()
    }
}

@Preview
@Composable
internal fun SplashComposablePreview() {
    AppTheme { SplashComposable(splashViewModel()) }
}

internal fun splashViewModel(): SplashViewModel = object : SplashViewModel() {
    override fun onSplashComplete() = Unit
}
