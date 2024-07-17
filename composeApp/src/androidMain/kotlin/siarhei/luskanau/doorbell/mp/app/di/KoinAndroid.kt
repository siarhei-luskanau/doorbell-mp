package siarhei.luskanau.doorbell.mp.app.di

import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.app
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appPlatformModule: Module = module {
    single<FirebaseApp> {
        val context: Context = get()
        com.google.firebase.FirebaseApp.initializeApp(context)
        Firebase.app
    }
}
