import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.hotReload)
    alias(libs.plugins.multiplatform)
    id("testOptionsConvention")
}

kotlin {
    jvmToolchain(libs.versions.build.jvmTarget.get().toInt())

    androidTarget {
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.koin.compose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(projects.navigation)
            implementation(projects.sharedCore.coreCommon)
            implementation(projects.sharedCore.coreFirebase)
            implementation(projects.sharedUi.uiAuth)
            implementation(projects.sharedUi.uiPermissions)
            implementation(projects.sharedUi.uiSplash)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.jetbrains.navigation.compose)
        }

        androidNativeTest.dependencies {
            implementation(libs.androidx.monitor)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.splashscreen)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        jvmTest.dependencies {
            implementation(kotlin("test"))
        }

        jsMain.dependencies {
        }

        iosMain.dependencies {
        }
    }
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
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/res")
    }
    testOptions.configureTestOptions()
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
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
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MultiplatformApp"
            packageVersion = "1.0.0"

            linux {
                iconFile.set(project.file("desktopAppIcons/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("desktopAppIcons/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("desktopAppIcons/MacosIcon.icns"))
                bundleID = "org.company.app.desktopApp"
            }
        }
    }
}

dependencies {
    // https://developer.android.com/develop/ui/compose/testing#setup
    androidTestImplementation(libs.androidx.compose.test.junit4)
    debugImplementation(libs.androidx.compose.test.manifest)

    // KSP Tasks
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspJs", libs.koin.ksp.compiler)
    add("kspJvm", libs.koin.ksp.compiler)
    debugImplementation(libs.leakcanary.android)
    // https://developer.android.com/develop/ui/compose/testing#setup
    debugImplementation(libs.androidx.compose.test.manifest)
    implementation(libs.androidx.compose.test.junit4)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

apply(plugin = "com.google.gms.google-services")
