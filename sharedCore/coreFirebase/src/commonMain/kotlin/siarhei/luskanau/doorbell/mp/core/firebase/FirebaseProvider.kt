package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.auth.FirebaseAuth

interface FirebaseProvider {
    suspend fun getFirebaseApp(): FirebaseApp
    suspend fun getFirebaseAuth(): FirebaseAuth
}
