package siarhei.luskanau.doorbell.mp.ui.imagedetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class ImageDetailsViewModel : ViewModel() {
    abstract val viewState: StateFlow<String>

    abstract fun onEvent(event: ImageDetailsViewEvent)
}
