import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    kotlin("plugin.compose")
}

kotlin {
    jvmToolchain(libs.findVersion("javaVersion").get().requiredVersion.toInt())

    androidLibrary {
        compileSdk = libs.findVersion("build-android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("build-android-minSdk").get().requiredVersion.toInt()

        compilerOptions {
            jvmTarget.set(
                JvmTarget.fromTarget(libs.findVersion("javaVersion").get().requiredVersion)
            )
        }

        withDeviceTest {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
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
        // iosX64(),
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
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material3AdaptiveNavigationSuite)
            implementation(compose.materialIconsExtended)
            implementation(compose.runtime)
            implementation(compose.runtimeSaveable)
            implementation(compose.ui)
            implementation(compose.uiUtil)
            implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel-compose").get())
            implementation(libs.findLibrary("jetbrains-navigation-compose").get())
            implementation(libs.findLibrary("koin-compose").get())
            implementation(libs.findLibrary("kotlinx-coroutines-core").get())
            implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
        }

        commonTest.dependencies {
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.findLibrary("kotlinx-coroutines-swing").get())
        }

        jsMain.dependencies {
        }

        iosMain.dependencies {
        }
    }
}

tasks.withType<AbstractTestTask>().configureEach {
    failOnNoDiscoveredTests = false
}
