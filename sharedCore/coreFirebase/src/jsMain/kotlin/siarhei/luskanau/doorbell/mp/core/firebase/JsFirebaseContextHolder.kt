package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.FirebaseOptions
import org.koin.core.annotation.Single

@Single
internal class JsFirebaseContextHolder : FirebaseContextHolder {
    override fun getContext(): Any = Unit
    override fun getFirebaseOptions() = FirebaseOptions(
        applicationId = google_app_id,
        apiKey = google_api_key,
        databaseUrl = firebase_database_url,
        storageBucket = google_storage_bucket,
        projectId = project_id,
        gcmSenderId = gcm_defaultSenderId
    )
}
