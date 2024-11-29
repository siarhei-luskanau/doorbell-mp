import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
    kotlin("plugin.compose")
}

kotlin {
    jvmToolchain(libs.findVersion("build-jvmTarget").get().requiredVersion.toInt())

    androidTarget {
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

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
            implementation(compose.materialIconsExtended)
            implementation(compose.runtime)
            implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel-compose").get())
            implementation(libs.findLibrary("jetbrains-navigation-compose").get())
            implementation(libs.findLibrary("koin-annotations").get())
            implementation(libs.findLibrary("koin-core").get())
            implementation(libs.findLibrary("kotlinx-coroutines-core").get())
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
            implementation(compose.html.core)
        }

        iosMain.dependencies {
        }
    }
}

android {
    compileSdk = libs.findVersion("build-android-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("build-android-minSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures.compose = true
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
        targetCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
    }
    packaging.resources.excludes.add("META-INF/**")
}

dependencies {
    // KSP Tasks
    add("kspAndroid", libs.findLibrary("koin-ksp-compiler").get())
    add("kspCommonMainMetadata", libs.findLibrary("koin-ksp-compiler").get())
    add("kspIosArm64", libs.findLibrary("koin-ksp-compiler").get())
    add("kspIosSimulatorArm64", libs.findLibrary("koin-ksp-compiler").get())
    add("kspIosX64", libs.findLibrary("koin-ksp-compiler").get())
    add("kspJs", libs.findLibrary("koin-ksp-compiler").get())
    add("kspJvm", libs.findLibrary("koin-ksp-compiler").get())
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
