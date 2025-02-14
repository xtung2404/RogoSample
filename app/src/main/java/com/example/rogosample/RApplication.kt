package com.example.rogosample

import android.app.Application
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.app.AndroidIoTPlatform
import rogo.iot.module.rogocore.sdk.SmartSdk

class RApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SmartSdk.isForceStagingSvr = true
//        SmartSdk.isForceProduction = true
        SmartSdk.isSupportModelDevelopment = true
        SmartSdk.isV2DeviceBleConfig = true
        AndroidIoTPlatform(this, false)
//        SmartSdk().initV2(
//            "f07b9dc8912e44ed8b4c6e895acd02c2",
//            "731eee3c8c8ca3de3b178264b7a6a13e80d42f1f1bc1",
//        )
            //FPT
//        SmartSdk().initV2(
//            AndroidIoTPlatform(this, false),
//            "f78f5dd2fc594475a27bef7c2caf9ab4",
//            "41d96be770b2902f801b1689c5edae29c16a068e8f87",
//        )
//        SAFEFIRE_STAGING
        SmartSdk().initV2(
            "78c4807471bf498fa0dd943b1fd4ff9a",
            "060863465ebe478d569a565d439a76dda4fc79690c3b",
        )
        ILogR.setEnablePrint(true)
    }
}