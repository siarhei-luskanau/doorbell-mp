import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.ksp)
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
            implementation(compose.html.core)
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
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.company.app.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    // https://developer.android.com/develop/ui/compose/testing#setup
    androidTestImplementation(libs.androidx.uitest.junit4)
    debugImplementation(libs.androidx.uitest.testManifest)

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
