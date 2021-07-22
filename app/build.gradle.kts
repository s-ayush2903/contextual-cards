plugins {
    with(Plugins.BuildPlugins) {
        id(application)
        id(kotlinAndroid)
    }
}

android {
    compileSdkVersion(Sdk.compileSdk)
    buildToolsVersion(Plugins.Versions.buildTools)

    defaultConfig {
        applicationId("in.stvayush.contextualcards")
        minSdkVersion(Sdk.minSdk)
        targetSdkVersion(Sdk.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner(Dependencies.androidJunitRunner)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
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
    with(Dependencies) {
        implementation(stdLib)
        implementation(androidxCoreKtx)
        implementation(appCompat)
        implementation(materialDesign)
        implementation(constraintLayout)
        
        // Retrofit & Other networking libs
        implementation(coreRetrofit)
        implementation(gsonConverter)
        implementation(httpInterceptor)

        // RxJava
        implementation(rxJava)
        implementation(rxAndroid)

        // Retrofit to RxJava Converter factory
        implementation(retrofitRxAdapter)

        // Glide
        implementation(glide)
        implementation(glideAnnotationProcessor)

        // SharedPrefs-ktx
        implementation(sharedPreferencesKtx)

        // Shimmer for RecyclerView
        implementation(shimmerRecyclerView)

        // SwipeRefresh Layout
        implementation(swipeRefreshLayout)

        testImplementation(junit)
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}
