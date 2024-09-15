package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.FirebaseOptions
import org.koin.core.annotation.Single

@Single
internal class IosFirebaseContextHolder : FirebaseContextHolder {
    override fun getContext(): Any = Unit
    override fun getFirebaseOptions(): FirebaseOptions? = null
}
