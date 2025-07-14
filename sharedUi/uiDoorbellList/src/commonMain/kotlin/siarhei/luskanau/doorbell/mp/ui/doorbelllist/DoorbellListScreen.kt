package siarhei.luskanau.doorbell.mp.ui.doorbelllist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DoorbellListScreen(viewModelProvider: () -> DoorbellListViewModel) {
    val viewModel = viewModel { viewModelProvider() }
    DoorbellListContent(onEvent = viewModel::onEvent)
}

@Composable
internal fun DoorbellListContent(onEvent: (DoorbellListViewEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Example static list, replace with ViewModel-backed list as needed
        listOf("Front Door", "Back Door", "Garage").forEach { doorbell ->
            Text(
                text = doorbell,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onEvent(DoorbellListViewEvent.DoorbellClicked(doorbell)) }
            )
        }
    }
}

@Preview
@Composable
internal fun DoorbellListScreenPreview() {
    DoorbellListContent(onEvent = {})
}
