plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.compose.screenshot)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlinx.serialization)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":sharedCore:coreFirebase"))
            implementation(project(":sharedUi:uiCommon"))
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.ui.auth"
    testOptions.configureTestOptions()
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
