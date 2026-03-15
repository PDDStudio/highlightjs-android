plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.pddstudio.highlightjs"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        targetSdk = 35
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.pddstudio"
                artifactId = "highlightjs-android"
                version = "2.0.0"

                pom {
                    name.set("highlightjs-android")
                    description.set("HighlightJs View for Syntax-Highlighting on Android")
                    url.set("https://github.com/pddstudio/highlightjs-android")

                    licenses {
                        license {
                            name.set("The Apache Software License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("pddstudio")
                            name.set("Patrick J")
                        }
                    }

                    scm {
                        url.set("https://github.com/pddstudio/highlightjs-android")
                        connection.set("scm:git@github.com:pddstudio/highlightjs-android.git")
                        developerConnection.set("scm:git@github.com:pddstudio/highlightjs-android.git")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/pddstudio/highlightjs-android")
                credentials {
                    username = System.getenv("GITHUB_ACTOR") ?: (project.findProperty("gpr.user") as String?)
                    password = System.getenv("GITHUB_TOKEN") ?: (project.findProperty("gpr.key") as String?)
                }
            }
        }
    }
}
