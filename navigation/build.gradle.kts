plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
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

android {
    namespace = "siarhei.luskanau.doorbell.mp.navigation"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}
