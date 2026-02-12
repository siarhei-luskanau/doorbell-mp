plugins {
    id("gsParserConvention")
    id("kotlinMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.buildConfig)
}

kotlin {
    androidLibrary {
        namespace = "siarhei.luskanau.doorbell.mp.core.firebase"
        // testOptions.configureTestOptions()
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.gitlive.firebase.auth)
            implementation(projects.sharedCore.coreCommon)
        }
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
        }
    }
}

buildConfig {
    packageName("siarhei.luskanau.doorbell.mp.core.firebase")
    useKotlinOutput {
        topLevelConstants = true
        internalVisibility = true
    }
    val googleServicesJsonParser = GoogleServicesJsonParser(
        googleServicesJsonFile = File(
            File(File(rootProject.rootDir, "app"), "androidApp"),
            "google-services.json"
        ),
        packageName = "siarhei.luskanau.doorbell.mp.app"
    )
    listOf(
        "jvmMain",
        "jsMain"
        // "wasmJsMain"
    ).forEach { sourceSetName ->
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
