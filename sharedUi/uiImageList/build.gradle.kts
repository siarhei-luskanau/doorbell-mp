plugins {
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    // alias(libs.plugins.compose.screenshot)
}

kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.ui.imagelist"
        // testOptions.configureTestOptions()
        experimentalProperties["android.experimental.enableScreenshotTest"] = true
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedUi.uiCommon)
        }
    }
}

dependencies {
    // screenshotTestImplementation(libs.screenshot.validation.api)
}
