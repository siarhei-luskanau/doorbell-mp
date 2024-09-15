package siarhei.luskanau.doorbell.mp.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.apps
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import siarhei.luskanau.doorbell.mp.core.common.DispatcherSet

@Single
internal class FirebaseProviderImpl(
    private val dispatcherSet: DispatcherSet,
    private val firebaseContextHolder: FirebaseContextHolder
) : FirebaseProvider {

    private var firebaseApp: FirebaseApp? = null
    private var firebaseAuth: FirebaseAuth? = null

    override suspend fun getFirebaseApp(): FirebaseApp = firebaseApp ?: let {
        val context: Any = firebaseContextHolder.getContext()
        withContext(dispatcherSet.ioDispatcher()) {
            val options = firebaseContextHolder.getFirebaseOptions()
            val app = Firebase.apps(context).firstOrNull() ?: Firebase.initialize(
                context = context,
                options = requireNotNull(options)
            )
            firebaseApp = app
            app
        }
    }

    override suspend fun getFirebaseAuth(): FirebaseAuth = firebaseAuth ?: let {
        val app = getFirebaseApp()
        withContext(dispatcherSet.ioDispatcher()) {
            Firebase.auth(app)
                .also { firebaseAuth = it }
        }
    }
}
