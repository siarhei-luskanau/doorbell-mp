plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.common"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

compose.resources {
    publicResClass = true
    packageOfResClass = "siarhei.luskanau.doorbell.mp.ui.common"
    generateResClass = always
}
