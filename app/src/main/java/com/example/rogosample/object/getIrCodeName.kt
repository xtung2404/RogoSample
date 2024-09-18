package com.example.rogosample.`object`

import com.example.rogosample.R
import rogo.iot.module.rogocore.basesdk.define.IoTAttribute
import rogo.iot.module.rogocore.basesdk.define.IoTErrorCode
import rogo.iot.module.rogocore.basesdk.define.IoTIrCode

fun getIrCodeName(irCode: Int): Int =
    when (irCode) {
        IoTIrCode.NUM_0 -> R.string.num_0
        IoTIrCode.NUM_1 -> R.string.num_1
        IoTIrCode.NUM_2 -> R.string.num_2
        IoTIrCode.NUM_3 -> R.string.num_3
        IoTIrCode.NUM_4 -> R.string.num_4
        IoTIrCode.NUM_5 -> R.string.num_5
        IoTIrCode.NUM_6 -> R.string.num_6
        IoTIrCode.NUM_7 -> R.string.num_7
        IoTIrCode.NUM_8 -> R.string.num_8
        IoTIrCode.NUM_9 -> R.string.num_9
        IoTIrCode.POWER -> R.string.power
        IoTIrCode.POWER_ON -> R.string.power_on
        IoTIrCode.POWER_OFF -> R.string.power_off
        IoTIrCode.FAN_SPEED -> R.string.fan_speed
        IoTIrCode.UP -> R.string.up
        IoTIrCode.DOWN -> R.string.down
        IoTIrCode.LEFT -> R.string.left
        IoTIrCode.RIGHT -> R.string.right
        IoTIrCode.BACK -> R.string.back
        IoTIrCode.HOME -> R.string.home
        IoTIrCode.CHANNEL_UP -> R.string.channel_up
        IoTIrCode.CHANNEL_DOWN -> R.string.channel_down
        IoTIrCode.CHANNEL_LIST -> R.string.channel_list
        IoTIrCode.VOL_UP -> R.string.volume_up
        IoTIrCode.VOL_DOWN -> R.string.volume_down
        IoTIrCode.MENU -> R.string.menu
        IoTIrCode.MUTE -> R.string.mute
        else -> R.string.undefined
    }