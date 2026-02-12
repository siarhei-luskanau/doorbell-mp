plugins {
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    // alias(libs.plugins.compose.screenshot)
}

kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.ui.permissions"
        // testOptions.configureTestOptions()
        experimentalProperties["android.experimental.enableScreenshotTest"] = true
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedUi.uiCommon)
        }
        androidMain.dependencies {
            implementation(libs.moko.permissions.camera)
            implementation(libs.moko.permissions.compose)
        }
        iosMain.dependencies {
            implementation(libs.moko.permissions.camera)
            implementation(libs.moko.permissions.compose)
        }
    }
}

dependencies {
    // screenshotTestImplementation(libs.screenshot.validation.api)
}
