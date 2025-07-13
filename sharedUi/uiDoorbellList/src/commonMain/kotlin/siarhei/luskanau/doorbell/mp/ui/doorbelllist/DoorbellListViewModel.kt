package siarhei.luskanau.doorbell.mp.ui.doorbelllist

import androidx.lifecycle.ViewModel

abstract class DoorbellListViewModel : ViewModel() {
    abstract fun onEvent(event: DoorbellListViewEvent)
}
