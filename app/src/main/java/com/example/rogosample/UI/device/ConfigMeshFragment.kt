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
import com.example.rogosample.adapter.HubDeviceAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentConfigMeshBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rogo.iot.module.rogocore.basesdk.ILogR
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceType
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.DiscoveryIoTBleCallback
import rogo.iot.module.rogocore.sdk.callback.GetDeviceAvailableCallback
import rogo.iot.module.rogocore.sdk.callback.SetupDeviceCallback
import rogo.iot.module.rogocore.sdk.entity.IoTBleScanned
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import rogo.iot.module.rogocore.sdk.entity.IoTWileScanned
import rogo.iot.module.rogocore.sdk.entity.SetupDeviceInfo

class ConfigMeshFragment : BaseFragment<FragmentConfigMeshBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_config_mesh
    private val deviceList = arrayListOf<IoTDevice>()
    private val deviceMap = hashMapOf<String, IoTBleScanned>()
    private var ioTBleScanned: IoTBleScanned?= null

    private val deviceSpinnerAdapter by lazy {
        DeviceSpinnerAdapter(requireContext(), deviceList)
    }
    private val groupSpinnerAdapter by lazy {
        GroupSpinnerAdapter(requireContext(), SmartSdk.groupHandler().all.toMutableList())
    }
    override fun initVariable() {
        super.initVariable()
        deviceList.clear()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.add_ble)
            spinnerHub.adapter = deviceSpinnerAdapter
            spinnerGroup.adapter = groupSpinnerAdapter
        }
    }

    override fun initAction() {
        super.initAction()
        /*
        * Check available hub
        * */
        SmartSdk.configMeshHandler().checkMeshGatewayAvailable(object: GetDeviceAvailableCallback {
            override fun onDeviceAvailable(devId: String?) {
                deviceList.add(SmartSdk.deviceHandler().get(devId))
                deviceSpinnerAdapter.notifyDataSetChanged()
            }
        })
        binding.apply {
            btnStartScan.setOnClickListener {
                dialogLoading.show()
                startScan()
            }

            btnAddDevice.setOnClickListener {
                setUpDevice(ioTBleScanned!!)
            }
        }
    }
    private fun startScan() {
        deviceMap.clear()
        /*
        * Scan nearby BLE devices of which RSSI are greater than -70. Check if Bluetooth is available.
        * */
        SmartSdk.configMeshHandler().discoveryMeshDevice(object : DiscoveryIoTBleCallback {
            override fun onMeshDeviceFound(ioTBleScanned: IoTBleScanned?) {
                ioTBleScanned?.let {
                    if(it.ioTProductModel != null && it.rssi > -70) {
                        deviceMap[it.uuidMesh] = it
                    }
                    ILogR.D("deviceAvailable", it.uuidMesh)
                }
                dialogLoading.dismiss()
            }

            override fun onRequirePermision() {
                dialogLoading.dismiss()

            }

            override fun onRequireEnableBluetooth() {
                dialogLoading.dismiss()
                showNoti(R.string.turn_on_the_bluetooth)
            }

            override fun onBluetoothError() {
                dialogLoading.dismiss()
            }
        })
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            if(deviceMap.isNotEmpty()) {
                pickBestDevice()
                dialogLoading.dismiss()
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(30000)
            stopDiscovery()
            if(deviceMap.isNotEmpty()) {
                pickBestDevice()
                dialogLoading.dismiss()
            } else {
                dialogLoading.dismiss()
                if(ioTBleScanned == null) {
                    showNoti(R.string.no_available_device)
                }
            }
        }
    }
    /*
    * Stop scan devices
    * */
    private fun stopDiscovery() {
        SmartSdk.configMeshHandler().stopDiscovery()
    }

    /*
    * Get the closest device
    * */
    private fun pickBestDevice() {
        var bestDevice = ""
        var bestRssi = -9999
        for(entry in deviceMap.entries) {
            if(entry.value.rssi > bestRssi) {
                bestDevice = entry.key
                bestRssi = entry.value.rssi
                if(bestRssi > -45) {
                    break
                }
            }
        }
        if(deviceMap.isNotEmpty()) {
            startConfig(deviceMap[bestDevice]!!)
            deviceMap.remove(bestDevice)
        }
    }

    /*
    * config the device scanned
    * */
    private fun startConfig(ioTBleScanned: IoTBleScanned) {
        binding.lnDevice.visibility = View.VISIBLE
        this.ioTBleScanned = ioTBleScanned
        binding.edtDeviceName.setText(ioTBleScanned.ioTProductModel.name)
        SmartSdk.configMeshHandler().preSetupDevice(
            (binding.spinnerHub.selectedItem as IoTDevice).uuid,
            ioTBleScanned.uuidMesh, object: SetupDeviceCallback {
                override fun onProgress(id: String?, progress: Int, msg: String?) {
                    binding.txtProgress.text = progress.toString()
                }

                override fun onSuccess(ioTDevice: IoTDevice?) {
                    showNoti(getString(R.string.connect_to, ioTDevice!!.label))
                }

                override fun onSetupFailure(errorCode: Int, msg: String) {
                    binding.txtProgress.text = msg
                }

            }
        )
    }

    /*
    * Set up the device scanned
    * */
    private fun setUpDevice(ioTBleScanned: IoTBleScanned) {
        SmartSdk.configMeshHandler().startSetupDevice(
            SetupDeviceInfo(
                ioTBleScanned.uuidMesh,
                binding.edtDeviceName.text.toString(),
                (binding.spinnerGroup.selectedItem as IoTGroup).uuid,
                ioTBleScanned.ioTProductModel.modelId,
                ioTBleScanned.ioTProductModel.devSubType
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}