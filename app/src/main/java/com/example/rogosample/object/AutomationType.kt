package com.example.rogosample.`object`

import rogo.iot.module.rogocore.basesdk.define.IoTAttribute
import rogo.iot.module.rogocore.basesdk.define.IoTCmdConst

enum class AutomationType(
    val automationAttr: Int,
    val automationType: Int?= null
) {
    ON_OFF(IoTAttribute.ONOFF),
    ON(IoTAttribute.ONOFF, IoTCmdConst.POWER_ON),
    OFF(IoTAttribute.ONOFF, IoTCmdConst.POWER_OFF),
    OPEN_CLOSE(IoTAttribute.OPEN_CLOSE_EVT),
    OPEN(IoTAttribute.OPEN_CLOSE_EVT, IoTCmdConst.OPENCLOSE_MODE_OPEN),
    CLOSE(IoTAttribute.OPEN_CLOSE_EVT, IoTCmdConst.OPENCLOSE_MODE_CLOSE),
    LOCK_UNLOCK(IoTAttribute.LOCK_UNLOCK),
    LOCK(IoTAttribute.LOCK_UNLOCK, IoTCmdConst.DOOR_LOCKED),
    UNLOCK(IoTAttribute.LOCK_UNLOCK, IoTCmdConst.DOOR_UNLOCKED),
    MOUNT_UNMOUNT(IoTAttribute.WALL_MOUNTED_EVT),
    MOUNT(IoTAttribute.WALL_MOUNTED_EVT, IoTCmdConst.WAll_MOUNTED),
    UNMOUNT(IoTAttribute.WALL_MOUNTED_EVT, IoTCmdConst.WAll_NOT_MOUNT),
    SMOKE_DETECTED(IoTAttribute.SMOKE_EVT),
    TEMP_CHANGE(IoTAttribute.TEMP_EVT),
    ALL_PRESS_BUTTON(IoTAttribute.BUTTON_PRESS_EVT),
    SINGLE_PRESS_BUTTON(IoTAttribute.BUTTON_PRESS_EVT, IoTCmdConst.BTN_PRESS_SINGLE),
    DOUBLE_PRESS_BUTTON(IoTAttribute.BUTTON_PRESS_EVT, IoTCmdConst.BTN_PRESS_DOUBLE),
    LONG_PRESS_BUTTON(IoTAttribute.BUTTON_PRESS_EVT, IoTCmdConst.BTN_PRESS_LONG),
    PRESENCE_UNPRESENCE(IoTAttribute.PRESENSCE_EVT),
    PRESENCE_DETECTED(IoTAttribute.PRESENSCE_EVT, 1),
    PRESENCE_UNDETECTED(IoTAttribute.PRESENSCE_EVT, 0),
    BOTH_MOTION(IoTAttribute.MOTION_EVT),
    MOTION_DETECTED(IoTAttribute.MOTION_EVT, 1),
    MOTION_UNDETECTED(IoTAttribute.MOTION_EVT, 0),
    LUX_CHANGE(IoTAttribute.LUX_EVT);

    companion object {
        fun getAutomationTypeList() = arrayListOf<AutomationType>(
            ON_OFF,
            ON,
            OFF,
            OPEN_CLOSE,
            OPEN,
            CLOSE,
            LOCK_UNLOCK,
            LOCK,
            UNLOCK,
            MOUNT_UNMOUNT,
            MOUNT,
            UNMOUNT,
            SMOKE_DETECTED,
            TEMP_CHANGE,
            ALL_PRESS_BUTTON,
            SINGLE_PRESS_BUTTON,
            DOUBLE_PRESS_BUTTON,
            LONG_PRESS_BUTTON,
            PRESENCE_UNPRESENCE,
            PRESENCE_DETECTED,
            PRESENCE_UNDETECTED,
            LUX_CHANGE
        )
        fun getAutoTypePos(attr: Int): Int {
            var pos = 0
            for(i in (0 until getAutomationTypeList().size)){
                if(attr == getAutomationTypeList()[i].automationAttr) {
                    pos = i
                }
            }
            return pos
        }

        fun getAutoTypePos(attr: Int, cmd: Int): Int {
            var pos = 0
            for(i in (0 until getAutomationTypeList().size)){
                if(attr == getAutomationTypeList()[i].automationAttr && cmd == getAutomationTypeList()[i].automationType) {
                    pos = i
                }
            }
            return pos
        }
    }

}