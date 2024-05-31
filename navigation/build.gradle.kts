plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.navigation"
    testOptions.configureTestOptions()
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":sharedCore:coreCommon"))
            implementation(project(":sharedUi:uiCyclone"))
            implementation(project(":sharedUi:uiSplash"))
        }
    }
}
