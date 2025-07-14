package siarhei.luskanau.doorbell.mp.ui.imagelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.doorbell.mp.ui.common.theme.AppTheme

@Composable
fun ImageListScreen(viewModelProvider: () -> ImageListViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    ImageListContent(
        viewState = viewModel.viewState,
        onEvent = viewModel::onEvent
    )
}

@Composable
internal fun ImageListContent(viewState: StateFlow<String>, onEvent: (ImageListViewEvent) -> Unit) {
    val viewState = viewState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Showing list for ${viewState.value}")
        // Example static list, replace with ViewModel-backed list as needed
        listOf("Image1", "Image2", "Image3").forEach { image ->
            Text(
                text = image,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onEvent(ImageListViewEvent.ImageClicked(image)) }
            )
        }
    }
}

@Preview
@Composable
internal fun ImageListScreenPreview() {
    AppTheme {
        ImageListContent(
            viewState = MutableStateFlow("Doorbell!"),
            onEvent = {}
        )
    }
}
