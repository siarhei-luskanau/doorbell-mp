package siarhei.luskanau.doorbell.mp.app

import androidx.test.platform.app.InstrumentationRegistry
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.auth.auth
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class FirebaseAppAndroidTest {

    @Ignore("Firebase test")
    @Test
    fun testAndroidApp() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            actual = targetContext.applicationContext::class.qualifiedName,
            expected = AndroidApp::class.qualifiedName
        )
    }

    @Ignore("Firebase test")
    @Test
    fun testFirebaseApp() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertNotNull(
            actual = (targetContext.applicationContext as AndroidApp).koin.get<FirebaseApp>()
        )
    }

    @Ignore("Firebase test")
    @Test
    fun testFirebaseAuth() {
        runTest {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            (targetContext.applicationContext as AndroidApp).koin.get<FirebaseApp>().let {
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
