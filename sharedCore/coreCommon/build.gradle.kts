plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
}

kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.core.common"
        // testOptions.configureTestOptions()
    }
}
