plugins {
    id("gsParserConvention")
    id("kotlinMultiplatformConvention")
    id("testOptionsConvention")
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.google.ksp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.gitlive.firebase.auth)
            implementation(projects.sharedCore.coreCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.doorbell.mp.core.firebase"
    testOptions.configureTestOptions()
}

dependencies {
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

buildConfig {
    packageName("siarhei.luskanau.doorbell.mp.core.firebase")
    useKotlinOutput {
        topLevelConstants = true
        internalVisibility = true
    }
    val googleServicesJsonParser = GoogleServicesJsonParser(
        googleServicesJsonFile = File(
            File(rootProject.rootDir, "composeApp"),
            "google-services.json"
        ),
        packageName = "siarhei.luskanau.doorbell.mp.app"
    )
    listOf("jvmMain", "jsMain").forEach { sourceSetName ->
        sourceSets.getByName(sourceSetName) {
            buildConfigField(
                "String",
                "google_api_key",
                "\"${googleServicesJsonParser.googleApiKey()}\""
            )
            buildConfigField(
                "String",
                "google_app_id",
                "\"${googleServicesJsonParser.googleAppId()}\""
            )
            buildConfigField(
                "String",
                "firebase_database_url",
                "\"${googleServicesJsonParser.firebaseDatabaseUrl()}\""
            )
            buildConfigField(
                "String",
                "gcm_defaultSenderId",
                "\"${googleServicesJsonParser.gcmDefaultSenderId()}\""
            )
            buildConfigField(
                "String",
                "google_storage_bucket",
                "\"${googleServicesJsonParser.googleStorageBucket()}\""
            )
            buildConfigField("String", "project_id", "\"${googleServicesJsonParser.projectId()}\"")
        }
    }
}
