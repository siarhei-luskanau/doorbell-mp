package siarhei.luskanau.doorbell.mp.ui.imagedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ImageDetailsScreen(viewModelProvider: () -> ImageDetailsViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    ImageDetailsContent(
        viewState = viewModel.viewState,
        onEvent = viewModel::onEvent
    )
}

@Composable
internal fun ImageDetailsContent(
    viewState: StateFlow<String>,
    onEvent: (ImageDetailsViewEvent) -> Unit
) {
    val viewState = viewState.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Replace with actual image loading logic
        Text(text = "Showing details for ${viewState.value}")
    }
}

@Preview
@Composable
internal fun ImageDetailsScreenPreview() {
    ImageDetailsContent(viewState = MutableStateFlow("Image!"), onEvent = {})
}
