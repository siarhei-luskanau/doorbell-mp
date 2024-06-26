package siarhei.luskanau.doorbell.mp.app

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.initKoin
import siarhei.luskanau.doorbell.mp.navigation.AppComposable

class AndroidApp : Application() {
    override fun onCreate() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                // .detectAll() for all detectable problems
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                // .penaltyDeath()
                .build()
        )
        super.onCreate()
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val koin = initKoin(
            module {
                single<Context> { applicationContext }
            }
        ).koin
        setContent {
            AppComposable(koin = koin)
        }
    }
}
