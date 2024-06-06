package siarhei.luskanau.doorbell.mp.app.di

import android.app.Application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appPlatformModule: Module = module {
    single<FirebaseApp> {
        FirebasePlatform.initializeFirebasePlatform(
            object : FirebasePlatform() {
                val storage = mutableMapOf<String, String>()
                override fun store(key: String, value: String) = storage.set(key, value)
                override fun retrieve(key: String) = storage[key]
                override fun clear(key: String) {
                    storage.remove(key)
                }
                override fun log(msg: String) = println(msg)
            }
        )
        val options = FirebaseOptions(
            projectId = "",
            applicationId = "",
            apiKey = "",
            databaseUrl = "",
            gaTrackingId = null,
            storageBucket = "",
            gcmSenderId = null,
            authDomain = null
        )
        Firebase.initialize(Application(), options)
    }
}
