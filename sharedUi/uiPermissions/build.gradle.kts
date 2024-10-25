plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.google.ksp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedUi.uiCommon)
        }
        androidMain.dependencies {
            implementation(libs.moko.permissions.compose)
        }
        iosMain.dependencies {
            implementation(libs.moko.permissions.compose)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.permissions"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
