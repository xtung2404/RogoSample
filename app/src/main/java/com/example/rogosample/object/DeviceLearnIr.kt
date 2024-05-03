package com.example.rogosample.`object`

import androidx.annotation.StringRes
import com.example.rogosample.R
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceType

enum class DeviceLearnIr(
    val type: Int,
    @StringRes val deviceName: Int
) {
    FAN(IoTDeviceType.FAN, R.string.fan),
    TV(IoTDeviceType.TV, R.string.TV),
    AIRCON(IoTDeviceType.AC, R.string.air_con);

    companion object {
        fun getDeviceTypes() = arrayListOf<DeviceLearnIr>(
            FAN,
            TV
        )
    }
}