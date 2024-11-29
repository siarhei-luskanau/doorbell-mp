plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.core.common"
    testOptions.configureTestOptions()
}
