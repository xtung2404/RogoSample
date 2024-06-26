package com.example.rogosample

import android.app.Application
import android.graphics.Bitmap.Config
import com.google.firebase.FirebaseApp
import rogo.iot.module.rogocore.basesdk.ILogR
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.handler.AuthHandler

class RApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SmartSdk.isForceProduction = false

//        if (!SmartSdk.isForceProduction) {
//            SmartSdk().initV2(
//                this,
//                "c43766743d294724a4a672c48ef7b3e5",
//                "20dad14f1153b64f8f2405027b009b60bd21f97907d8",
//                RogoNotificationImpl(), false, true
//            )
//            return
//        }
//        SmartSdk().initV2(
//            this,
//            "907a15d2bdfa4dd5aba1914e97dc7146",
//            "5b077bf50d654369e7e5bc83c3c2f310d3a020136aab",
//            RogoNotificationImpl(), false, true
//        )

//        if (!BuildConfig.DEBUG)
//            SmartSdk.isForceProduction = true
        SmartSdk().initV2(
            this,
            "fa751d67ddcf4e0dabe03d29ec81bac5",
            "1de6ed021df6a4cf4d3a178f918a414df1f290da20ef",
            RogoNotificationImpl(), false, true
        )
        ILogR.setEnablePrint(true)
//        initEnviroment()
    }
}