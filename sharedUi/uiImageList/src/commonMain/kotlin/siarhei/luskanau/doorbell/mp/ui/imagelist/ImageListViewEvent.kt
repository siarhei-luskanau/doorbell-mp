package siarhei.luskanau.doorbell.mp.ui.imagelist

sealed interface ImageListViewEvent {
    data class ImageClicked(val imageId: String) : ImageListViewEvent
}
