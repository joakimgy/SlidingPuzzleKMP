import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    id("com.codingfeline.buildkonfig") version "0.15.1"
}

fun getLocalProperty(key: String, file: String = "local.properties"): String {
    val properties = Properties()
    val localProperties = File(file)
    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("File from not found")

    return properties.getProperty(key)
}


buildkonfig {
    packageName = "puzzle.sliding.SlidingPuzzle"

    defaultConfigs {
        defaultConfigs {
            buildConfigField(
                FieldSpec.Type.STRING,
                "UNSPLASH_API",
                getLocalProperty("UNSPLASH_API")
            )

        }
        /* buildConfigField(
             com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
             "API_KEY", ((project.findProperty("API_KEY") ?: "") as String)
         )*/
    }
}

/*buildkonfig {
    packageName = "com.example.app"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
        buildConfigField(STRING, "name", "value")
    }
}*/

kotlin {
    /* @OptIn(ExperimentalWasmDsl::class)
     wasmJs {
         moduleName = "composeApp"
         browser {
             commonWebpackConfig {
                 outputFileName = "composeApp.js"
             }
         }
         binaries.executable()
     }*/

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.camera.camera2)
            implementation(libs.androidx.camera.lifecycle)
            implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
            implementation(libs.androidx.camera.view)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)


            // Navigation
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)

            // Networking
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.serialization)

            // SVG & Images
            implementation(libs.coil)
            implementation(libs.coil.compose)
            implementation(libs.coil.network)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // File picker
            implementation(libs.mp.filepicker)

            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.desktop.webcam.capture)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.0-RC2")

        }
    }
}

android {
    namespace = "puzzle.sliding"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "puzzle.sliding"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "puzzle.sliding"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}
