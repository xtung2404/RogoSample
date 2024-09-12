// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

//    ext {
//        ext.ble_version = "2.3.1"
//        ext.nordic_ble_scanner = "1.6.0"
//        ext.gson_ver = '2.9.0'
//        ext.lifecycle_ver="2.2.0"
//        ext.lottie_ver="4.2.1"
//        ext.appcompat_ver="1.4.1"
//        ext.material_ver="1.2.1"
//        ext.okhttp = "4.10.0"
//        ext.room_version = "2.4.2"
//        ext.butter_knife = "10.2.3"
//        ext.paho_client = "1.2.5"
//        ext.paho_service = "1.1.1"
//        ext.work_version = "2.7.1"
//        ext.firebasemessage = "23.0.2"
//        ext.andoidx_version = "1.7.0"
//        ext.localboardcast = "1.1.0"
//        ext.rxdns_version  ="0.9.17"
//        ext.esptouch_version  ="2.2.0"
//    }



    dependencies {
        var nav_version = "2.4.1"
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//        classpath ("com.google.gms:google-services:4.4.2")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id ("com.android.library") version "8.2.0" apply false

    id ("org.jetbrains.kotlin.android") version "1.9.0-RC" apply false
    id ("androidx.navigation.safeargs") version "2.5.2" apply false
}
