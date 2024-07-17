package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize

class FirebaseService {

    init {
        val options = FirebaseOptions(
            projectId = "fir-database-f6b5a",
            applicationId = "siarhei.luskanau.doorbell.mp.app",
            apiKey = "",
            databaseUrl = null,
            gaTrackingId = null,
            storageBucket = null,
            gcmSenderId = null,
            authDomain = null
        )
        Firebase.initialize(null, options)
    }
    suspend fun isSignedIn(): Boolean = Firebase.auth.currentUser != null
}
