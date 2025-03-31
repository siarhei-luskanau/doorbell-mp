package siarhei.luskanau.doorbell.mp.app

import android.app.Application
import android.os.StrictMode

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        com.google.firebase.auth.FirebaseAuth.getInstance()
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
    }
}
