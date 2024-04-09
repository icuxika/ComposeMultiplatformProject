import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)

    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.desktop.uiTestJUnit4)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.desktop.components.splitPane)
        }
    }
}


// https://github.com/JetBrains/compose-multiplatform/blob/master/tutorials/Native_distributions_and_local_execution/README.md#basic-usage
compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            macOS {
                iconFile.set(project.file("../icons/application.icns"))
            }
            windows {
                iconFile.set(project.file("../icons/application.ico"))
                upgradeUuid = "299447C2-0241-453B-AB83-E1E53F3D6783"
                perUserInstall = true
                menu = true
                shortcut = true
            }
            linux {
                iconFile.set(project.file("../icons/application.png"))
            }

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeMultiplatformProject"
            packageVersion = "1.0.0"
            description = "Compose Example App"
            copyright = "© 2023 icuxika. All rights reserved."
            vendor = "My Manufacturer Name"
            licenseFile.set(project.file("../License.rtf"))
        }
    }
}

compose.experimental {
    web.application {}
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_21.toString()
    targetCompatibility = JavaVersion.VERSION_21.toString()
}