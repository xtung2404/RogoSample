package com.example.rogosample.`object`

import androidx.annotation.StringRes
import com.example.rogosample.R
import rogo.iot.module.rogocore.basesdk.define.IoTAttribute
import rogo.iot.module.rogocore.basesdk.define.IoTCmdConst

enum class Command(
    val cmdAttribute : Int,
    val cmdType: Int,
    @StringRes val cmdName: Int
) {
    ON(IoTAttribute.ONOFF, IoTCmdConst.POWER_ON, R.string.power_on),
    OFF(IoTAttribute.ONOFF, IoTCmdConst.POWER_OFF, R.string.power_off),
    SYNCON(IoTAttribute.SYNC_ONOFF_NORMAL, 0, R.string.sync_on),
    SYNCOFF(IoTAttribute.SYNC_ONOFF_REVERSING, 0, R.string.sync_off),
    OPEN(IoTAttribute.OPEN_CLOSE_CTL, IoTCmdConst.OPENCLOSE_MODE_OPEN, R.string.open),
    CLOSE(IoTAttribute.OPEN_CLOSE_CTL, IoTCmdConst.OPENCLOSE_MODE_CLOSE, R.string.close),
    BRIGHTNESS(IoTAttribute.BRIGHTNESS_KELVIN, -1, R.string.brightness),
    COLOR(IoTAttribute.COLOR_HSV, -2, R.string.color),
    AIRCONTROL(IoTAttribute.AC, -3, R.string.air_con);

    companion object {
        fun getCmdList() = arrayListOf<Command>(
            ON,
            OFF,
            SYNCON,
            SYNCOFF,
            OPEN,
            CLOSE,
            BRIGHTNESS,
            COLOR,
            AIRCONTROL
        )

        fun getCmdPos(attr: Int?, cmd: Int?): Int {
            var pos = -1
            for(i in (0 until getCmdList().size)) {
                if (getCmdList()[i].cmdAttribute == attr && getCmdList()[i].cmdType == cmd) {
                    pos = i
                }
            }
            return pos
        }

        fun getCmdPos(attr: Int?): Int {
            var pos = -1
            for(i in (0 until getCmdList().size)) {
                if (getCmdList()[i].cmdAttribute == attr) {
                    pos = i
                }
            }
            return pos
        }
    }
}