plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    // alias(libs.plugins.compose.screenshot)
}

kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.ui.common"
        // testOptions.configureTestOptions()
        experimentalProperties["android.experimental.enableScreenshotTest"] = true
    }
    sourceSets {
        commonMain.dependencies {
        }
    }
}

dependencies {
    // screenshotTestImplementation(libs.screenshot.validation.api)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "siarhei.luskanau.doorbell.mp.ui.common"
    generateResClass = always
}
