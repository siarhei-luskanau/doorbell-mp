package siarhei.luskanau.doorbell.mp.ui.doorbelllist

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
internal class DoorbellListViewModelImpl(
    @InjectedParam private val navigationCallback: DoorbellListNavigationCallback
) : DoorbellListViewModel() {

    override fun onEvent(event: DoorbellListViewEvent) {
        when (event) {
            is DoorbellListViewEvent.DoorbellClicked ->
                navigationCallback.onDoorbellSelected(doorbellId = event.doorbellId)
        }
    }
}
