plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":sharedCore:coreCommon"))
            // implementation(project(":sharedCore:coreFirebase"))
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.splash"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}
