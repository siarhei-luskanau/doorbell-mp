plugins {
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedUi.uiCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.imagedetails"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    screenshotTestImplementation(libs.screenshot.validation.api)
}
