plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.pddstudio.highlightjs.demo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pddstudio.highlightjs.demo"
        minSdk = 21
        targetSdk = 35
        versionCode = 2
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources.excludes += setOf(
            "META-INF/LICENSE.txt",
            "META-INF/LICENSE",
            "META-INF/NOTICE.txt"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(project(":library"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.github.api)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.swiperefreshlayout)
}
