package siarhei.luskanau.doorbell.mp.app

import android.app.Application
import android.content.Context
import android.os.StrictMode
import org.koin.core.Koin
import org.koin.dsl.module
import siarhei.luskanau.doorbell.mp.app.di.initKoin

class AndroidApp : Application() {

    val koin: Koin by lazy {
        initKoin(
            module {
                single<Context> { applicationContext }
            }
        ).koin
    }

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
        registerActivityLifecycleCallbacks(
            AppActivityLifecycleCallbacks { activity -> (activity as? AppActivity)?.koin = koin }
        )
        super.onCreate()
    }
}
