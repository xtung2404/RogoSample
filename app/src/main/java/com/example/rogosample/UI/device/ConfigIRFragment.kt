package com.example.rogosample.UI.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.adapter.GroupSpinnerAdapter
import com.example.rogosample.adapter.WifiSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentConfigIRBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rogo.iot.module.rogocore.basesdk.define.IoTWifiConnectionState
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.DiscoveryIoTWileCallback
import rogo.iot.module.rogocore.sdk.callback.SetupDeviceWileCallback
import rogo.iot.module.rogocore.sdk.entity.IoTBleScanned
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import rogo.iot.module.rogocore.sdk.entity.IoTWileScanned
import rogo.iot.module.rogocore.sdk.entity.SetupDeviceInfo

class ConfigIRFragment : BaseFragment<FragmentConfigIRBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_config_i_r

    private val deviceMap = hashMapOf<String, IoTWileScanned>()
    private val wifiMap = hashMapOf<String, Int>()
    private val wifiList = arrayListOf<String>()
    private var ioTWileScanned: IoTWileScanned? = null
    private val wifiSpinnerAdapter by lazy {
        WifiSpinnerAdapter(requireContext(), wifiList)
    }
    private val groupSpinnerAdapter by lazy {
        GroupSpinnerAdapter(requireContext(), SmartSdk.groupHandler().all.toList())
    }

    override fun initVariable() {
        super.initVariable()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.add_device_ir)
            spinnerGroup.adapter = groupSpinnerAdapter
            spinnerWifi.adapter = wifiSpinnerAdapter
        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            btnStartScan.setOnClickListener {
                dialogLoading.show()
                startScan()
            }

            btnConnect.setOnClickListener {
                lnDevice.visibility = View.GONE
                binding.lnSetup.visibility = View.VISIBLE
            }
            btnAddDevice.setOnClickListener {
                setUp()
            }
            /*
            * set up Internet for IR
            * */
            btnSetWifi.setOnClickListener {
                SmartSdk.configWileDeviceHandler().setWifiPwd(
                    (spinnerWifi.selectedItem as String),
                    edtPassword.text.toString(),
                    true
                )
            }
        }
    }


    private fun startScan() {
        stopDiscovery()
        deviceMap.clear()
        /*
        * Scan for available Wile devices
        * */
        SmartSdk.configWileDeviceHandler().discoveryWileDevice { ioTWileScanned ->
            ioTWileScanned?.let {
                if (it.ioTProductModel != null && it.rssi > -90) {
                    deviceMap[it.mac] = it
                }
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(15000)
            if (deviceMap.isNotEmpty()) {
                pickBestDevice()
                stopDiscovery()
                dialogLoading.dismiss()
            } else {
                dialogLoading.dismiss()
                showNoti(R.string.no_available_device)
            }
        }
    }

    /*
    * Stop scan
    * */
    private fun stopDiscovery() {
        SmartSdk.configWileDeviceHandler().stopDiscovery()
    }

    /*
    * Get the closest device
    * */
    private fun pickBestDevice() {
        stopDiscovery()
        var bestDevice = ""
        var bestRssi = -9999
        for (entry in deviceMap.entries) {
            if (entry.value.rssi > bestRssi) {
                bestDevice = entry.key
                bestRssi = entry.value.rssi
                if (bestRssi > -45) {
                    break
                }
            }
        }
        if (deviceMap.isNotEmpty()) {
            startConfig(deviceMap[bestDevice]!!)
            deviceMap.remove(bestDevice)
        }
    }

    /*
    * Config the device scanned
    * */
    private fun startConfig(ioTWileScanned: IoTWileScanned) {
        this.ioTWileScanned = ioTWileScanned
        stopDiscovery()
        binding.lnDevice.visibility = View.VISIBLE
        binding.txtDeviceName.text = ioTWileScanned.ioTProductModel.name
        binding.edtDeviceName.setText(ioTWileScanned.ioTProductModel.name)
    }

    /*
    * Set up the device scanned
    * */
    private fun setUp() {
        wifiMap.clear()
        wifiList.clear()
        ioTWileScanned?.let {
            val setupDeviceInfo = SetupDeviceInfo(
                it.mac,
                binding.edtDeviceName.text.toString(),
                (binding.spinnerGroup.selectedItem as IoTGroup).uuid,
                it.ioTProductModel.modelId
            )
            /*
            * Get the wifi list
            * */
//            SmartSdk.configWileDeviceHandler()(
//                setupDeviceInfo,
//                object : SetupDeviceWileCallback {
//                    //                    override fun onProgress(id: String?, progress: Int, msg: String?) {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            binding.txtProgress.text = progress.toString()
////                        }
////                    }
////
////                    override fun onSuccess(ioTDevice: IoTDevice?) {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            showNoti(getString(R.string.connect_to, ioTDevice?.label))
////                        }
////                    }
////
////                    override fun onSetupFailure(errorCode: Int, msg: String) {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            showNoti(msg)
////                        }
////                    }
////
////                    override fun onWifiScanned(ssid: String, auth: Int, rssi: Int) {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            wifiList.add(ssid)
////                            wifiSpinnerAdapter.notifyDataSetChanged()
////                        }
////                    }
////
////                    override fun onWifiStopScanned() {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            binding.lnWifi.visibility = View.VISIBLE
////                            wifiSpinnerAdapter.notifyDataSetChanged()
////                        }
////                    }
////
////                    override fun onWifiSetted() {
////                        CoroutineScope(Dispatchers.Main).launch {
////                            showNoti("wifi setted")
////                        }
////                    }
////
////                    override fun onWifiSsidInfo(status: Int, ssid: String?) {
////                        if(
////                            status == IoTWifiConnectionState.SOMETHING_WENT_WRONG ||
////                            status == IoTWifiConnectionState.PASSWORD_WRONG ||
////                            status == IoTWifiConnectionState.SSID_NOTFOUND
////                            ) {
////                                CoroutineScope(Dispatchers.Main).launch {
////                                    showNoti(R.string.wifi_problem)
////                                }
////                        }
////                    }
////
////                })
//                    override fun onProgress(p0: String, p1: Int, p2: String?) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            binding.txtProgress.text = p1.toString()
//                        }
//                    }
//
//                    override fun onSuccess() {
//
//                    }
//
//                    override fun onSetupFailure(p0: Int, p1: String) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            showNoti(p1)
//                        }
//                    }
//
//                    override fun onWifiScanned(p0: String, p1: Int, p2: Int) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            wifiList.add(p0)
//                            wifiSpinnerAdapter.notifyDataSetChanged()
//                        }
//                    }
//
//                    override fun onWifiStopScanned() {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            binding.lnWifi.visibility = View.VISIBLE
//                            wifiSpinnerAdapter.notifyDataSetChanged()
//                        }
//                    }
//
//                    override fun onWifiSetted() {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            showNoti("wifi setted")
//                        }
//                    }
//
//                    override fun onWifiSsidInfo(p0: Int, p1: String?) {
//                        if (
//                            p0 == IoTWifiConnectionState.SOMETHING_WENT_WRONG ||
//                            p0 == IoTWifiConnectionState.PASSWORD_WRONG ||
//                            p0 == IoTWifiConnectionState.SSID_NOTFOUND
//                        ) {
//                            CoroutineScope(Dispatchers.Main).launch {
//                                showNoti(R.string.wifi_problem)
//                            }
//                        }
//                    }
//                })
        }
    }
}