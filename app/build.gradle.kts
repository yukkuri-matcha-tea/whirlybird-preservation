plugins {
    id("com.android.application")
}

android {
    namespace = "com.google.android.play.games"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.google.android.play.games.whirlybird"
        minSdk = 23
        targetSdk = 35
        versionCode = 4
        versionName = "preservation-4"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    androidResources {
        noCompress += "ogg"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
