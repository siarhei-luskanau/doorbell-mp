import org.jetbrains.compose.ExperimentalComposeLibrary

val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("kotlinMultiplatformConvention")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
        }

        commonTest.dependencies {
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        jsMain.dependencies {
            implementation(compose.html.core)
        }
    }
}

android {
    buildFeatures.compose = true
}

compose.experimental {
    web.application {}
}
