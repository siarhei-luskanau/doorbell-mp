plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.splash"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}
