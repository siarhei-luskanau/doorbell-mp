plugins {
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.kotlinx.serialization)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.kotlinx.serialization.json)
            implementation(projects.sharedCore.coreFirebase)
            implementation(projects.sharedUi.uiCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.auth"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    screenshotTestImplementation(libs.screenshot.validation.api)
}
