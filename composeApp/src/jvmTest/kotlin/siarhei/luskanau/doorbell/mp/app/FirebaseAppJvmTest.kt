package siarhei.luskanau.doorbell.mp.app

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.auth.auth
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.initKoin

class FirebaseAppJvmTest {

    @Ignore("Firebase test")
    @Test
    fun testFirebaseAuth() {
        runTest {
            val koin = initKoin(module {}).koin
            koin.get<FirebaseApp>().let {
                Firebase.auth.signOut()
                try {
                    Firebase.auth.signInWithEmailAndPassword(
                        email = "email@email.email",
                        password = "password"
                    )
                } catch (error: Throwable) {
                    assertTrue(
                        actual = error.message.orEmpty()
                            .contains("There is no user record corresponding to this identifier")
                    )
                }
            }
        }
    }
}
