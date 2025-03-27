plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.hotelapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hotelapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        
        // Add Room schemas location
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }
    
    buildFeatures {
        viewBinding = true
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
    }
    
    testOptions {
        unitTests.all {
            it.enabled = true
        }
    }
    
    // Configuración para ignorar advertencias de SDK
    lint {
        disable.add("ObsoleteSdkInt")
        checkReleaseBuilds = false
        abortOnError = false
    }
    
    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt",
                "META-INF/DEPENDENCIES"
            )
            pickFirsts += listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }
    
    // Añadir esta configuración para prevenir problemas con el SDK
    androidResources {
        noCompress += "xml"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    
    // Room - actualizado a la última versión estable
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // ViewModel y LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Calendar View alternatives
    implementation("com.github.kizitonwose:CalendarView:1.0.4")
    
    // Gson
    implementation("com.google.code.gson:gson:2.10.1")
    
    // Desugaring para soporte de características de Java 8 en APIs antiguas
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    
    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}