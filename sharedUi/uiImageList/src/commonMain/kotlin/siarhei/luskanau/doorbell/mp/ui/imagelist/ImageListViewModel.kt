package siarhei.luskanau.doorbell.mp.ui.imagelist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class ImageListViewModel : ViewModel() {
    abstract val viewState: StateFlow<String>
    abstract fun onEvent(event: ImageListViewEvent)
}
