package com.example.rogosample.UI.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.adapter.GroupSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentConfigZigbeeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import no.nordicsemi.android.ble.callback.SuccessCallback
import rogo.iot.module.platform.callback.RequestCallback
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceSubType
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceType
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.CheckDeviceAvailableCallback
import rogo.iot.module.rogocore.sdk.callback.DiscoveryIoTBleCallback
import rogo.iot.module.rogocore.sdk.callback.PairZigbeeDeviceCallback
//import rogo.iot.module.rogocore.sdk.callback.DiscoveryIoTZigbeeCallback
//import rogo.iot.module.rogocore.sdk.callback.GetZigbeeAvailableCallback
//import rogo.iot.module.rogocore.sdk.callback.SuccessCallback
import rogo.iot.module.rogocore.sdk.entity.IoTBleScanned
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import rogo.iot.module.rogocore.sdk.entity.IoTPairedZigbeeDevice
//import rogo.iot.module.rogocore.sdk.entity.IoTZigbeePaired
import rogo.iot.module.rogocore.sdk.entity.SetupDeviceInfo

class ConfigZigbeeFragment : BaseFragment<FragmentConfigZigbeeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_config_zigbee
    private val deviceList = arrayListOf<IoTDevice>()
    private var scanningTime = 60
    private val deviceMap = hashMapOf<String, IoTPairedZigbeeDevice>()
    private var device: IoTPairedZigbeeDevice?= null
    private var gatewayId = ""
    private var deviceSubType = -1
    private val devType = IoTDeviceType.USB_DONGLE
    private val deviceSpinnerAdapter by lazy {
        DeviceSpinnerAdapter(requireContext(), deviceList)
    }
    private val groupSpinnerAdapter by lazy {
        GroupSpinnerAdapter(requireContext(), SmartSdk.groupHandler().all.toMutableList())
    }
    override fun initVariable() {
        super.initVariable()
        binding.apply {
            binding.apply {
                toolbar.btnBack.setOnClickListener {
                    findNavController().popBackStack()
                }
                toolbar.txtTitle.text = resources.getString(R.string.add_zigbee_device)
                spinnerHub.adapter = deviceSpinnerAdapter
                spinnerGroup.adapter = groupSpinnerAdapter
            }
        }
    }

    override fun initAction() {
        super.initAction()
        binding.btnStartScan.setOnClickListener {
            startScan()
        }
        binding.spinnerHub.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.btnStartScan.visibility = View.VISIBLE
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.btnStartScan.visibility = View.GONE
            }

        }
        SmartSdk.configZigbeeDeviceHandler().checkNetworkAvailable(object: CheckDeviceAvailableCallback  {
            override fun onDeviceAvailable(p0: String?) {
                deviceList.add(SmartSdk.deviceHandler().get(p0))
                deviceSpinnerAdapter.notifyDataSetChanged()
            }
        })
        binding.btnAddDevice.setOnClickListener {
            setUp()
        }
        binding.btnCurtain.setOnClickListener {
            deviceSubType = IoTDeviceSubType.DC_CTL_CURTAIN_TYPE
        }
        binding.btnGate.setOnClickListener {
            deviceSubType = IoTDeviceSubType.DC_CTL_GATE_TYPE
        }
    }
    private fun startScan() {
        stopDiscovery()
        gatewayId = (binding.spinnerHub.selectedItem as IoTDevice).uuid
        deviceMap.clear()
        SmartSdk.configZigbeeDeviceHandler().startPairingZigbee(gatewayId, scanningTime, devType, object : PairZigbeeDeviceCallback {
            override fun onPairedDevice(p0: IoTPairedZigbeeDevice?) {
                device = p0
                binding.edtDeviceName.setText(p0!!.ioTProductModel.name)
                binding.lnDevice.visibility = View.VISIBLE
                stopDiscovery()
            }

            override fun onPairedUnknownDevice(p0: String?, p1: String?, p2: String?) {

            }
        })
        CoroutineScope(Dispatchers.Default).launch {
            delay(10000)
            stopDiscovery()
            if(device == null) {
                showNoti(R.string.no_available_device)
            }
        }
    }
    private fun stopDiscovery() {
        SmartSdk.configZigbeeDeviceHandler().stopPairingZigbee(gatewayId)
    }
    private fun setUp() {
        device?.let {
            SmartSdk.configZigbeeDeviceHandler().addZigbeeDevice(
                gatewayId, it,binding.edtDeviceName.text.toString(),
                (binding.spinnerGroup.selectedItem as IoTGroup).uuid,
                deviceSubType,
                object: RequestCallback<IoTDevice> {
                    override fun onFailure(errorCode: Int, message: String?) {
                        message?.let {
                            showNoti(it)
                        }
                    }

                    override fun onSuccess(item: IoTDevice?) {
                        showNoti(getString(R.string.connect_to, item!!.label))
                    }
                }
            )
        }
    }
}