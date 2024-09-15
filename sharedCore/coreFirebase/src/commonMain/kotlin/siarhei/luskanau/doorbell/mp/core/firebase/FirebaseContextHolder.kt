package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.FirebaseOptions

internal interface FirebaseContextHolder {
    fun getContext(): Any
    fun getFirebaseOptions(): FirebaseOptions?
}
