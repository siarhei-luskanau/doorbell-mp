package siarhei.luskanau.doorbell.mp.ui.imagelist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
internal class ImageListViewModelImpl(
    @InjectedParam private val doorbellId: String,
    @InjectedParam private val navigationCallback: ImageListNavigationCallback
) : ImageListViewModel() {

    override val viewState: StateFlow<String> = MutableStateFlow(doorbellId)

    override fun onEvent(event: ImageListViewEvent) {
        when (event) {
            is ImageListViewEvent.ImageClicked ->
                navigationCallback.onImageSelected(imageId = event.imageId)
        }
    }
}
