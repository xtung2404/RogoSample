package com.example.rogosample.`object`

import androidx.annotation.StringRes
import com.example.rogosample.R

enum class Function(
    @StringRes val funcName: Int
) {
    SIGNIN(R.string.sign_in),
    SIGNUP(R.string.sign_up),
    FORGOTPASSWORD(R.string.forgot_password),
    SIGNOUT(R.string.sign_out),
    GETLISTLOCATION(R.string.get_list_location),
    ADDLOCATION(R.string.add_location),
    EDITLOCATION(R.string.edit_location),
    DELETELOCATION(R.string.delete_location),
    GETLISTGROUP(R.string.get_list_group),
    ADDGROUP(R.string.add_group),
    EDITGROUP(R.string.edit_group),
    DELETEGROUP(R.string.delete_group),
    VIRTUALGROUP(R.string.virtual_group),
    CREATEVIRTUALGROUP(R.string.create_virtual_group),
    BINDMEMBERGROUP(R.string.bind_member_to_virtual_group),
    UNBINDMEMBERGROUP(R.string.unbind_member_to_virtual_group),
    GETLISTDEVICE(R.string.get_list_device),
    ADDDEVICE(R.string.add_device),
    EDITDEVICE(R.string.edit_device),
    DELETEDEVICE(R.string.delete_device),
    ADDBOX(R.string.add_box),
    ADDBLE(R.string.add_ble),
    ADDZIGBEE(R.string.add_zigbee_device),
    ADDWILEDEVICE(R.string.add_wile_device),
    ADDRFDEVICE(R.string.add_rf_device),
    ADDIRREMOTE(R.string.add_ir_remote),
    ADDIRREMOTEAC(R.string.add_ir_ac_remote),
    SMARTSCENARIO(R.string.smart_scenario),
    SMARTSCHEDULE(R.string.smart_schedule),
    SMARTAUTOMATION(R.string.smart_automation),
    GETLISTSMARTSCENARIO(R.string.get_list_smart_scenario),
    ADDSMARTSCENARIO(R.string.add_smart_scenario),
    EDITSMARTSCENARIO(R.string.edit_smart_scenario),
    DELETESMARTSCENARIO(R.string.delete_smart_scenario),
    GETLISTSMARTSCHEDULE(R.string.get_list_smart_schedule),
    ADDSMARTSCHEDULE(R.string.add_smart_schedule),
    EDITSMARTSCHEDULE(R.string.edit_smart_schedule),
    DELETESMARTSCHEDULE(R.string.delete_smart_schedule),
    GETLISTSMARTAUTOMATION(R.string.get_list_smart_automation),
    EDITSMARTAUTOMATION(R.string.edit_smart),
    ADDSMARTAUTOMATION(R.string.add_smart_automation),
    DELETESMARTAUTOMATION(R.string.delete_smart_automation);

    companion object {
        fun getAuthenFuncs() = arrayListOf<Function>(
            SIGNIN,
            SIGNUP,
            FORGOTPASSWORD,
            SIGNOUT
        )

        fun getLocationFuncs() = arrayListOf<Function>(
            GETLISTLOCATION,
            ADDLOCATION,
            EDITLOCATION,
            DELETELOCATION
        )

        fun getVirtualGroupFuncs() = arrayListOf<Function>(
            VIRTUALGROUP,
            CREATEVIRTUALGROUP,
            BINDMEMBERGROUP,
            UNBINDMEMBERGROUP
        )

        fun getGroupFuncs() = arrayListOf<Function>(
            GETLISTGROUP,
            ADDGROUP,
            EDITGROUP,
            DELETEGROUP
        )
        fun getDeviceFuncs() = arrayListOf<Function>(
            GETLISTDEVICE,
            ADDDEVICE,
            EDITDEVICE,
            DELETEDEVICE
        )

        fun getAddDeviceFuncs() = arrayListOf<Function>(
            ADDBOX,
            ADDBLE,
            ADDZIGBEE,
            ADDWILEDEVICE,
            ADDRFDEVICE,
            ADDIRREMOTE,
            ADDIRREMOTEAC
        )
        fun getSMARTFuncs() = arrayListOf<Function>(
            SMARTSCENARIO,
            SMARTAUTOMATION,
            SMARTSCHEDULE
        )

        fun getSmartSceneFuncs() = arrayListOf<Function>(
            GETLISTSMARTSCENARIO,
            ADDSMARTSCENARIO,
            EDITSMARTSCENARIO,
            DELETESMARTSCENARIO
        )

        fun getSmartScheduleFuncs() = arrayListOf<Function>(
            GETLISTSMARTSCHEDULE,
            ADDSMARTSCHEDULE,
            EDITSMARTSCHEDULE,
            DELETESMARTSCHEDULE
        )

        fun getSmartAutomationFuncs() = arrayListOf<Function>(
            GETLISTSMARTAUTOMATION,
            ADDSMARTAUTOMATION,
            EDITSMARTAUTOMATION,
            DELETESMARTAUTOMATION
            )


    }
}