plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
//    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.rogosample"
    compileSdk = 34

    defaultConfig {
        applicationId = "rogo.iot.app.thingedges"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/pluginIcon.svg"
            excludes += "META-INF/plugin.xml"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

var kotlin_version = "1.5.30"
var hilt_version = "2.42"
var coroutines_version = "1.5.1"
var room_version = "2.4.3"
var compose_navigation = "1.1.0"
var glide_version = "4.12.0"
var recovery_version = "1.0.0"
var moonlight = "26af56aafd"
var navigation = "2.5.1"
var lifecycle_version = "2.5.1"
var material_dialog_version = "3.3.0"
var data_store = "1.0.0"
var material_design = "1.5.0"
var lottie = "5.0.3"
var okhttp3 = "4.10.0"

var ble_version = "2.3.1"
var nordic_ble_scanner = "1.6.0"
var gson_ver = "2.9.0"
var paho_client = "1.2.5"
var paho_service = "1.1.1"
var work_version = "2.7.1"
var andoidx_version = "1.7.0"
var localboardcast = "1.1.0"
var rxdns_version = "0.9.17"
var firebase_ui = "6.2.0"
var firebase_auth_ver = "21.3.0"
var firebase_message_ver = "23.0.2"
var play_auth_service = "20.5.0"
var xml_to_json = "1.5.1"
var jdbc = "3.36.0"
var retrofit = "2.9.0"
var hilt_compiler = "1.1.0"
// ui
var circle_imageview = "3.1.0"
var bottom_bar = "1.1.0"
var fragment_version = "1.4.1"
var refresh_layout = "1.1.0"

// compose
var compose_version = "1.1.1"
var activity_compose = "1.4.0"
var doctor_compose_nav = "1.1.0"

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    implementation ("com.firebaseui:firebase-ui-auth:$firebase_ui")
    implementation ("com.google.firebase:firebase-messaging:$firebase_message_ver")
    implementation ("com.google.firebase:firebase-auth:$firebase_auth_ver")
    implementation ("com.google.firebase:firebase-iid:21.1.0")

    implementation ("com.airbnb.android:lottie:$lottie")

    implementation ("com.google.code.gson:gson:2.9.0")

    implementation ("com.github.andriydruk:rxdnssd:$rxdns_version")

    //ROOM
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.core:core:$andoidx_version")
    kapt ("androidx.room:room-compiler:$room_version")

//    implementation(files("libs/rogocore-release.aar", "libs/rogobase.jar", "libs/mesh-release.aar"))

//    implementation(group= "",name = "rogocore-release", ext = "aar")
//    implementation(group = "", name = "rogobase", ext = "jar")
//    implementation(group = "", name = "mesh-release", ext = "aar")
//    implementation(group = "", name = "rogocomponent", ext = "jar")

//    implementation(files("libs/rogocore-release"))
    implementation(group = "", name = "rogobase", ext = "jar")
    implementation(group = "", name = "rogocomponent", ext = "jar")
    implementation(group = "", name = "rogocore", ext = "jar")
    implementation(group = "", name = "rogocloudapi", ext = "jar")
    implementation(group = "", name = "rogoplatform", ext = "jar")
    implementation(group = "", name = "rogoplatformandroid-release", ext = "aar")
    implementation(group = "", name = "rogoutils", ext = "jar")
    implementation(group = "", name = "rogosigmesh", ext = "jar")


//    implementation(group = "", name = "thinguikit", ext = "jar")
//    implementation(group = "", name = "thingedgesuikit", ext = "jar")
//    implementation(group = "", name = "thingedgestheme", ext = "jar")

//    implementation project(path: ':rogobase')
//    implementation project(path: ':rogocomponent')
//    implementation project(path: ':rogocore')
//    implementation project(path: ':rogocloudapi')
//    implementation project(path: ':rogoplatform')
//    implementation project(path: ':rogoplatformandroid')
//    implementation project(path: ':thinguikit')
//    implementation project(path: ':thingedgesuikit')
//    implementation project(path: ':thingedgestheme')


    implementation ("org.bouncycastle:bcpkix-jdk15on:1.56")
    implementation ("com.madgag.spongycastle:core:1.58.0.0")
    implementation ("com.madgag.spongycastle:prov:1.58.0.0")

    implementation ("com.squareup.okhttp3:okhttp:$okhttp3")
    implementation ("io.github.rburgst:okhttp-digest:2.6")
    implementation ("com.github.smart-fun:XmlToJson:$xml_to_json")
    implementation ("org.bouncycastle:bcpkix-jdk15on:1.56")
    implementation ("com.github.andriydruk:rxdnssd:$rxdns_version")
    implementation ("com.google.code.gson:gson:$gson_ver")
    implementation ("org.eclipse.paho:org.eclipse.paho.mqttv5.client:$paho_client")
//For Base & Mesh
    implementation ("no.nordicsemi.android:ble:$ble_version")
    implementation ("no.nordicsemi.android.support.v18:scanner:$nordic_ble_scanner")
    implementation ("androidx.localbroadcastmanager:localbroadcastmanager:$localboardcast")
//ROOM
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.core:core:$andoidx_version")
    kapt ("androidx.room:room-compiler:$room_version")
// (Java only)
    implementation ("androidx.work:work-runtime:$work_version")
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

//    implementation("org.eclipse.paho:org.eclipse.paho.mqttv5.client:$paho_client")
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

}