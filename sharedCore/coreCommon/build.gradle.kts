plugins {
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.core.common"
    testOptions.configureTestOptions()
}

dependencies {
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
