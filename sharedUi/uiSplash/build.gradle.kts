plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.splash"
    testOptions.configureTestOptions()
}
