import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {

    androidTarget {
        compilations.all {
            compileTaskProvider {
                compilerOptions {
                    jvmTarget.set(
                        JvmTarget.fromTarget(
                            libs.findVersion("build-jvmTarget").get().requiredVersion
                        )
                    )
                    freeCompilerArgs.add(
                        "-Xjdk-release=${
                            JavaVersion.valueOf(
                                libs.findVersion("build-javaVersion").get().requiredVersion
                            )
                        }"
                    )
                }
            }
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    wasmJs {
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
            implementation(libs.findLibrary("koin-core").get())
            implementation(libs.findLibrary("lifecycle-viewmodel-compose").get())
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
        }

        jvmMain.dependencies {
        }

        jsMain.dependencies {
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
