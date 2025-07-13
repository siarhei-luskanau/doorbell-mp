package siarhei.luskanau.doorbell.mp.ui.imagedetails

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
internal class ImageDetailsViewModelImpl(
    @InjectedParam private val imageId: String,
    @InjectedParam private val navigationCallback: ImageDetailsNavigationCallback
) : ImageDetailsViewModel() {
    override val viewState: StateFlow<String> = MutableStateFlow(imageId)

    override fun onEvent(event: ImageDetailsViewEvent) {
        navigationCallback.toString()
    }
}
