package com.example.rogosample.`object`

import androidx.annotation.StringRes
import com.example.rogosample.R
import rogo.iot.module.platform.define.IoTCmdConst

enum class AcControlItem (
    val itemId: Int,
    @StringRes val itemName: Int,
    var isChecked: Boolean?= null
) {
    AC_MODE_AUTO(IoTCmdConst.AC_MODE_AUTO, R.string.auto),
    AC_MODE_DRY(IoTCmdConst.AC_MODE_DRY, R.string.dry),
    AC_MODE_COOL(IoTCmdConst.AC_MODE_COOLING, R.string.cool),
    AC_MODE_FAN(IoTCmdConst.AC_MODE_FAN, R.string.fan),
    AC_MODE_HEAT(IoTCmdConst.AC_MODE_HEATING, R.string.heat),
    FAN_SPEED_AUTO(IoTCmdConst.FAN_SPEED_AUTO, R.string.auto),
    FAN_SPEED_LOW(IoTCmdConst.FAN_SPEED_LOW, R.string.low),
    FAN_SPEED_NORMAL(IoTCmdConst.FAN_SPEED_NORMAL, R.string.normal),
    FAN_SPEED_HIGH(IoTCmdConst.FAN_SPEED_HIGH, R.string.high);

    companion object {
        fun getAcModeList() = arrayListOf(
            AC_MODE_AUTO,
            AC_MODE_DRY,
            AC_MODE_COOL,
            AC_MODE_FAN,
            AC_MODE_HEAT
        )

        fun getFanSpeedList() = arrayListOf(
            FAN_SPEED_AUTO,
            FAN_SPEED_LOW,
            FAN_SPEED_NORMAL,
            FAN_SPEED_HIGH
        )
    }

}