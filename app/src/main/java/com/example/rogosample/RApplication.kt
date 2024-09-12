package com.example.rogosample

import android.app.Application
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.app.AndroidIoTPlatform
import rogo.iot.module.rogocore.sdk.SmartSdk
import kotlin.math.truncate

class RApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SmartSdk.isForceProduction = true
        SmartSdk.isSupportModelDevelopment = true
        SmartSdk.isV2DeviceBleConfig = true
        SmartSdk().initV2(
            AndroidIoTPlatform(this, false),
            "e4b75a6b23fc4f30bd5fab35436c6a90",
            "964e2c974f001a0468bf2734ce88e96652afff328886",
        )
        ILogR.setEnablePrint(true)
    }
}