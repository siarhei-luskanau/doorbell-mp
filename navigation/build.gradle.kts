plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":sharedCore:coreCommon"))
            implementation(project(":sharedUi:uiAuth"))
            implementation(project(":sharedUi:uiCommon"))
            implementation(project(":sharedUi:uiPermissions"))
            implementation(project(":sharedUi:uiSplash"))
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.navigation"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}
