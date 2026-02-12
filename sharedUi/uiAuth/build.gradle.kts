plugins {
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    // alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.kotlinx.serialization)
}
kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.ui.auth"
        // testOptions.configureTestOptions()
        experimentalProperties["android.experimental.enableScreenshotTest"] = true
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.kotlinx.serialization.json)
            implementation(projects.sharedCore.coreFirebase)
            implementation(projects.sharedUi.uiCommon)
        }
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
        }
    }
}

dependencies {
    // screenshotTestImplementation(libs.screenshot.validation.api)
}
