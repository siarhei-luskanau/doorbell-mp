package siarhei.luskanau.doorbell.mp.app

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.plugin.module.dsl.koinConfiguration
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

@Preview
@Composable
fun KoinAppComposable() = KoinApplication(
    configuration = koinConfiguration<DiKoinApplication>()
) {
    AppComposable()
}
