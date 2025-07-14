plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(projects.sharedCore.coreCommon)
            implementation(projects.sharedUi.uiAuth)
            implementation(projects.sharedUi.uiCommon)
            implementation(projects.sharedUi.uiDoorbellList)
            implementation(projects.sharedUi.uiImageDetails)
            implementation(projects.sharedUi.uiImageList)
            implementation(projects.sharedUi.uiPermissions)
            implementation(projects.sharedUi.uiSplash)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.navigation"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    screenshotTestImplementation(libs.screenshot.validation.api)
}
