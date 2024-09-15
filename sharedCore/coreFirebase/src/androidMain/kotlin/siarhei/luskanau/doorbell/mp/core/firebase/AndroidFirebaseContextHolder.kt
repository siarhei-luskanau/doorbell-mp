package siarhei.luskanau.doorbell.mp.core.firebase

import android.content.Context
import dev.gitlive.firebase.FirebaseOptions
import org.koin.core.annotation.Single

@Single
internal class AndroidFirebaseContextHolder(private val context: Context) : FirebaseContextHolder {
    override fun getContext(): Any = context
    override fun getFirebaseOptions(): FirebaseOptions? = null
}
