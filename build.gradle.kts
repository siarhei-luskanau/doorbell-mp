@file:Suppress("PropertyName")

import groovy.json.JsonSlurper
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.util.Properties
import org.apache.tools.ant.taskdefs.condition.Os

println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
System.getProperties().forEach { key, value -> println("System.getProperties(): $key=$value") }
System.getenv().forEach { (key, value) -> println("System.getenv(): $key=$value") }

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.ksp).apply(false)
    alias(libs.plugins.google.services).apply(false)
    alias(libs.plugins.multiplatform).apply(false)
}

allprojects {
    apply(from = "$rootDir/ktlint.gradle")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        parallel = true
        ignoreFailures = false
    }
}

val CI_GRADLE = "CI_GRADLE"

tasks.register("devAll") {
    group = CI_GRADLE
    doLast {
        gradlew(
            "clean",
            "ktlintFormat"
        )
        gradlew(
            "ciLint",
            "ciUnitTest",
            "ciAndroid",
            "ciDesktop"
        )
        gradlew("ciIos")
        gradlew("ciBrowser")
        gradlew("updateDebugScreenshotTest")
        gradlew("validateDebugScreenshotTest")
        gradlew("jsBrowserProductionWebpack")
        gradlew("ciSdkManagerLicenses")
        gradlew("ciAndroidEmulator")
    }
}

tasks.register("ciLint") {
    group = CI_GRADLE
    doLast {
        gradlew(
            "ktlintCheck",
            "detekt",
            "lint"
        )
    }
}

tasks.register("ciUpdateScreenshot") {
    group = CI_GRADLE
    doLast {
        gradlew(
            "ktlintFormat",
            "updateDebugScreenshotTest"
        )
    }
}

tasks.register("ciUnitTest") {
    group = CI_GRADLE
    doLast {
        gradlew(":composeApp:jvmTest")
        gradlew("validateDebugScreenshotTest")
    }
}

tasks.register("ciAndroid") {
    group = CI_GRADLE
    doLast {
        gradlew("assembleDebug")
        copy {
            from(rootProject.subprojects.map { it.layout.buildDirectory })
            include("**/*.apk")
            exclude("**/apk/androidTest/**")
            eachFile { path = name }
            includeEmptyDirs = false
            into("${layout.buildDirectory.asFile.get().path}/apk/")
        }
    }
}

tasks.register("ciAndroidEmulator") {
    group = CI_GRADLE
    doLast {
        val tasks = mutableListOf(
            "managedVirtualDeviceDebugAndroidTest",
            "--no-parallel",
            "--max-workers=1",
            "-Pandroid.testoptions.manageddevices.emulator.gpu=swiftshader_indirect",
            "-Pandroid.experimental.testOptions.managedDevices.emulator.showKernelLogging=true"
        )
        if (!true.toString().equals(other = System.getenv("CI"), ignoreCase = true)) {
            tasks.add("--enable-display")
        }
        gradlew(*tasks.toTypedArray())
        gradlew("cleanManagedDevices")
    }
}

tasks.register("ciDesktop") {
    group = CI_GRADLE
    doLast {
        gradlew(":composeApp:jvmJar")
    }
}

tasks.register("ciBrowser") {
    group = CI_GRADLE
    doLast {
        gradlew(":composeApp:jsMainClasses")
    }
}

tasks.register("ciIos") {
    group = CI_GRADLE
    doLast {
        if (Os.isFamily(Os.FAMILY_MAC)) {
            runExec(listOf("brew", "install", "kdoctor"))
            runExec(listOf("kdoctor"))
            val devicesJson = runExec(
                listOf(
                    "xcrun",
                    "simctl",
                    "list",
                    "devices",
                    "available",
                    "-j"
                )
            )

            @Suppress("UNCHECKED_CAST")
            val devicesList = (JsonSlurper().parseText(devicesJson) as Map<String, *>)
                .let { it["devices"] as Map<String, *> }
                .let { devicesMap ->
                    devicesMap.keys
                        .filter { it.startsWith("com.apple.CoreSimulator.SimRuntime.iOS") }
                        .map { devicesMap[it] as List<*> }
                }
                .map { jsonArray -> jsonArray.map { it as Map<String, *> } }
                .flatten()
                .filter { it["isAvailable"] as Boolean }
                .filter {
                    listOf("iphone 1").any { device ->
                        (it["name"] as String).contains(device, true)
                    }
                }
            println("Devices:${devicesList.joinToString { "\n" + it["udid"] + ": " + it["name"] }}")
            val device = devicesList.firstOrNull()
            println("Selected:\n${device?.get("udid")}: ${device?.get("name")}")
            runExec(
                listOf(
                    "xcodebuild",
                    "-project",
                    "${rootDir.path}/iosAppComposeUi/iosApp.xcodeproj",
                    "-scheme",
                    "iosApp",
                    "-configuration",
                    "Debug",
                    "OBJROOT=${rootDir.path}/build/ios",
                    "SYMROOT=${rootDir.path}/build/ios",
                    "-destination",
                    "id=${device?.get("udid")}",
                    "-allowProvisioningDeviceRegistration",
                    "-allowProvisioningUpdates"
                )
            )
        }
    }
}

tasks.register("ciSdkManagerLicenses") {
    group = CI_GRADLE
    doLast {
        val sdkDirPath = getAndroidSdkPath(rootDir = rootDir)
        getSdkManagerFile(rootDir = rootDir)?.let { sdkManagerFile ->
            val yesInputStream = object : InputStream() {
                private val yesString = "y\n"
                private var counter = 0
                override fun read(): Int = yesString[counter % 2].also { counter++ }.code
            }
            providers.exec {
                executable = sdkManagerFile.absolutePath
                args = listOf("--list", "--sdk_root=$sdkDirPath")
                println("exec: ${this.commandLine.joinToString(separator = " ")}")
            }.apply { println("ExecResult: ${this.result.get()}") }
            @Suppress("DEPRECATION")
            exec {
                executable = sdkManagerFile.absolutePath
                args = listOf("--licenses", "--sdk_root=$sdkDirPath")
                standardInput = yesInputStream
                println("exec: ${this.commandLine.joinToString(separator = " ")}")
            }.apply { println("ExecResult: $this") }
        }
    }
}

fun runExec(commands: List<String>): String = object : ByteArrayOutputStream() {
    override fun write(p0: ByteArray, p1: Int, p2: Int) {
        print(String(p0, p1, p2))
        super.write(p0, p1, p2)
    }
}.let { resultOutputStream ->
    @Suppress("DEPRECATION")
    exec {
        if (System.getenv("JAVA_HOME") == null) {
            System.getProperty("java.home")?.let { javaHome ->
                environment = environment.toMutableMap().apply {
                    put("JAVA_HOME", javaHome)
                }
            }
        }
        commandLine = commands
        standardOutput = resultOutputStream
        println("commandLine: ${this.commandLine.joinToString(separator = " ")}")
    }.apply { println("ExecResult: $this") }
    String(resultOutputStream.toByteArray())
}

fun gradlew(vararg tasks: String, addToSystemProperties: Map<String, String>? = null) {
    providers.exec {
        executable = File(
            project.rootDir,
            if (Os.isFamily(Os.FAMILY_WINDOWS)) "gradlew.bat" else "gradlew"
        )
            .also { it.setExecutable(true) }
            .absolutePath
        args = mutableListOf<String>().also { mutableArgs ->
            mutableArgs.addAll(tasks)
            addToSystemProperties?.toList()?.map { "-D${it.first}=${it.second}" }?.let {
                mutableArgs.addAll(it)
            }
            mutableArgs.add("--stacktrace")
        }
        val sdkDirPath = Properties().apply {
            val propertiesFile = File(rootDir, "local.properties")
            if (propertiesFile.exists()) {
                load(propertiesFile.inputStream())
            }
        }.getProperty("sdk.dir")
        if (sdkDirPath != null) {
            val platformToolsDir = "$sdkDirPath${File.separator}platform-tools"
            val pathEnvironment = System.getenv("PATH").orEmpty()
            if (!pathEnvironment.contains(platformToolsDir)) {
                environment = environment.toMutableMap().apply {
                    put("PATH", "$platformToolsDir:$pathEnvironment")
                }
            }
        }
        if (System.getenv("JAVA_HOME") == null) {
            System.getProperty("java.home")?.let { javaHome ->
                environment = environment.toMutableMap().apply {
                    put("JAVA_HOME", javaHome)
                }
            }
        }
        if (System.getenv("ANDROID_HOME") == null) {
            environment = environment.toMutableMap().apply {
                put("ANDROID_HOME", sdkDirPath)
            }
        }
        println("commandLine: ${this.commandLine}")
    }.apply { println("ExecResult: ${this.result.get()}") }
}

fun getAndroidSdkPath(rootDir: File): String? = Properties().apply {
    val propertiesFile = File(rootDir, "local.properties")
    if (propertiesFile.exists()) {
        load(propertiesFile.inputStream())
    }
}.getProperty("sdk.dir").let { propertiesSdkDirPath ->
    (propertiesSdkDirPath ?: System.getenv("ANDROID_HOME"))
}

fun getSdkManagerFile(rootDir: File): File? = getAndroidSdkPath(
    rootDir = rootDir
)?.let { sdkDirPath ->
    println("sdkDirPath: $sdkDirPath")
    val files = File(sdkDirPath).walk().filter { file ->
        file.path.contains("cmdline-tools") && file.path.endsWith("sdkmanager")
    }
    files.forEach { println("walk: ${it.absolutePath}") }
    val sdkmanagerFile = files.firstOrNull()
    println("sdkmanagerFile: $sdkmanagerFile")
    sdkmanagerFile
}
