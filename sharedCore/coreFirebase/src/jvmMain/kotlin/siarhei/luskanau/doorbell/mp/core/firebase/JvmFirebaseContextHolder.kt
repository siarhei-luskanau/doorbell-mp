package siarhei.luskanau.doorbell.mp.core.firebase

import android.app.Application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.FirebaseOptions
import org.koin.core.annotation.Single

@Single
internal class JvmFirebaseContextHolder : FirebaseContextHolder {
    override fun getContext() = Application().apply {
        FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
            val storage = mutableMapOf<String, String>()
            override fun store(key: String, value: String) = storage.set(key, value)
            override fun retrieve(key: String) = storage[key]
            override fun clear(key: String) {
                storage.remove(key)
            }
            override fun log(msg: String) = println(msg)
        })
    }
    override fun getFirebaseOptions() = FirebaseOptions(
        applicationId = google_app_id,
        apiKey = google_api_key,
        databaseUrl = firebase_database_url,
        storageBucket = google_storage_bucket,
        projectId = project_id,
        gcmSenderId = gcm_defaultSenderId
    )
}
