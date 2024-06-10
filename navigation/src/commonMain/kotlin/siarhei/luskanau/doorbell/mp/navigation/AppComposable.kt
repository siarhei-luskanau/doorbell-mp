package siarhei.luskanau.doorbell.mp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.company.app.CycloneComposable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.Koin

@Preview
@Composable
fun AppComposable(koin: Koin) {
    Text("Navigation")
    CycloneComposable()
}
