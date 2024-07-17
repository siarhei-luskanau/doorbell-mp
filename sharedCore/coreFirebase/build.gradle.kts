plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.gitlive.firebase.auth)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.core.firebase"
    testOptions.configureTestOptions()
}
