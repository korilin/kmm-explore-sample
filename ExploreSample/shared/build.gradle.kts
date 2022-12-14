plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()
    arrayOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries {
            all {
                val mmkvPath = "${buildDir.absolutePath}/cocoapods/synthetic/IOS/Pods/MMKV"
                val mmkvCorePath = "${buildDir.absolutePath}/cocoapods/synthetic/IOS/Pods/MMKVCore"
                linkerOpts += listOf(
                    "-F$mmkvPath",
                    "-rpath", mmkvPath,
                    "-framework", "MMKV",
                    "-F$mmkvCorePath",
                    "-rpath", mmkvCorePath,
                    "-framework", "MMKVCore"
                )
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }

        pod(name = "MMKV") {
            version = "1.2.14"
            moduleName = "MMKV"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
                api("com.ctrip.flight.mmkv:mmkv-kotlin:1.2.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation("com.squareup.okhttp3:okhttp:4.10.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
            }
        }
        val androidTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:2.1.0")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}