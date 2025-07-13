package siarhei.luskanau.doorbell.mp.ui.doorbelllist

sealed interface DoorbellListViewEvent {
    data class DoorbellClicked(val doorbellId: String) : DoorbellListViewEvent
}
