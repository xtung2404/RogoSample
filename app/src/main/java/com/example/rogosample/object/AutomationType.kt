package com.example.rogosample.`object`

import rogo.iot.module.platform.define.IoTAttribute
import rogo.iot.module.platform.define.IoTCmdConst


enum class AutomationType(
    val automationAttr: Int,
    val automationType: Int?= null
) {
    ON_OFF(IoTAttribute.ACT_ONOFF),
    ON(IoTAttribute.ACT_ONOFF, IoTCmdConst.POWER_ON),
    OFF(IoTAttribute.ACT_ONOFF, IoTCmdConst.POWER_OFF),
    OPEN_CLOSE(IoTAttribute.ACT_OPEN_CLOSE),
    OPEN(IoTAttribute.ACT_OPEN_CLOSE, IoTCmdConst.OPENCLOSE_MODE_OPEN),
    CLOSE(IoTAttribute.ACT_OPEN_CLOSE, IoTCmdConst.OPENCLOSE_MODE_CLOSE),
    LOCK_UNLOCK(IoTAttribute.ACT_LOCK_UNLOCK),
    LOCK(IoTAttribute.ACT_LOCK_UNLOCK, IoTCmdConst.DOOR_LOCKED),
    UNLOCK(IoTAttribute.ACT_LOCK_UNLOCK, IoTCmdConst.DOOR_UNLOCKED),
    MOUNT_UNMOUNT(IoTAttribute.EVT_WALL_MOUNTED),
    MOUNT(IoTAttribute.EVT_WALL_MOUNTED, IoTCmdConst.WAll_MOUNTED),
    UNMOUNT(IoTAttribute.EVT_WALL_MOUNTED, IoTCmdConst.WAll_NOT_MOUNT),
    SMOKE_DETECTED(IoTAttribute.EVT_SMOKE),
    TEMP_CHANGE(IoTAttribute.EVT_TEMP),
    ALL_PRESS_BUTTON(IoTAttribute.EVT_BUTTON_PRESS),
    SINGLE_PRESS_BUTTON(IoTAttribute.EVT_BUTTON_PRESS, IoTCmdConst.BTN_PRESS_SINGLE),
    DOUBLE_PRESS_BUTTON(IoTAttribute.EVT_BUTTON_PRESS, IoTCmdConst.BTN_PRESS_DOUBLE),
    LONG_PRESS_BUTTON(IoTAttribute.EVT_BUTTON_PRESS, IoTCmdConst.BTN_PRESS_LONG),
    PRESENCE_UNPRESENCE(IoTAttribute.EVT_PRESENCE),
    PRESENCE_DETECTED(IoTAttribute.EVT_PRESENCE, 1),
    PRESENCE_UNDETECTED(IoTAttribute.EVT_PRESENCE, 0),
    BOTH_MOTION(IoTAttribute.EVT_MOTION),
    MOTION_DETECTED(IoTAttribute.EVT_MOTION, 1),
    MOTION_UNDETECTED(IoTAttribute.EVT_MOTION, 0),
    LUX_CHANGE(IoTAttribute.EVT_LUX);

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