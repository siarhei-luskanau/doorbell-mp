plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.app"
    compileSdk = libs.versions.build.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.build.android.minSdk.get().toInt()
        targetSdk = libs.versions.build.android.targetSdk.get().toInt()

        applicationId = "siarhei.luskanau.doorbell.mp.app"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions.configureTestOptions()
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
    }
    buildFeatures.compose = true
    signingConfigs {
        if (rootProject.file("debug.keystore").exists()) {
            getByName("debug") {
                storeFile = rootProject.file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
        if (rootProject.file("debug.keystore").exists()) {
            create("release") {
                storeFile = rootProject.file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }
    buildTypes {
        if (rootProject.file("debug.keystore").exists()) {
            getByName("debug") {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
        if (rootProject.file("debug.keystore").exists()) {
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
                isMinifyEnabled = true
            }
        }
    }
    packaging.resources.excludes.add("META-INF/**")
}

kotlin {
    jvmToolchain(libs.versions.javaVersion.get().toInt())
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
    implementation(projects.composeApp)

    debugImplementation(libs.leakcanary.android)

    // https://developer.android.com/develop/ui/compose/testing#setup
    androidTestImplementation(libs.androidx.compose.test.junit4)
    debugImplementation(libs.androidx.compose.test.manifest)
}

apply(plugin = "com.google.gms.google-services")
