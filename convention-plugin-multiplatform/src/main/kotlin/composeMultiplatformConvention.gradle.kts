val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("kotlinMultiplatformConvention")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
    }
}

android {
    buildFeatures.compose = true
}
