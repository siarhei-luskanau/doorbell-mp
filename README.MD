# Compose Multiplatform Application

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :app:androidApp:assembleDebug`
 - find `.apk` file in `app/androidApp/build/outputs/apk/debug/composeApp-debug.apk`
 - run android simulator UI tests: `./gradlew managedVirtualDeviceDebugAndroidTest`

### Desktop
 - run the desktop application: `./gradlew :composeApp:run`
 - run desktop UI tests: `./gradlew :composeApp:jvmTest`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
 - run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

### Experimental Browser (JS)
 - run the browser application: `./gradlew :composeApp:jsBrowserDevelopmentRun --continue`
 - run browser UI tests: `./gradlew :composeApp:jsBrowserTest`

### Experimental Browser (Wasm)
 - run the browser application: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun --continue`
 - run browser UI tests: `./gradlew :composeApp:wasmJsBrowserTest`
