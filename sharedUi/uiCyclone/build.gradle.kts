plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "siarhei.luskanau.doorbell.mp.ui.cyclones.resources"
    generateResClass = always
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.cyclone"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}
