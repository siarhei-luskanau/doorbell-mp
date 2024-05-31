plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.cyclone"
    testOptions.configureTestOptions()
}

compose.resources {
    publicResClass = true
    packageOfResClass = "siarhei.luskanau.doorbell.mp.ui.cyclones.resources"
    generateResClass = always
}
