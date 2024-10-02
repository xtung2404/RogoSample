package com.example.rogosample.UI.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.adapter.GroupSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentConfigRfDeviceBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rogo.iot.module.platform.callback.RequestCallback
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceSubType
import rogo.iot.module.rogocore.basesdk.define.IoTDeviceType
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.CheckDeviceAvailableCallback
import rogo.iot.module.rogocore.sdk.callback.PairRfDeviceCallback
import rogo.iot.module.rogocore.sdk.callback.PairZigbeeDeviceCallback
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import rogo.iot.module.rogocore.sdk.entity.IoTPairedRfDevice
import rogo.iot.module.rogocore.sdk.entity.IoTPairedZigbeeDevice

class ConfigRfDeviceFragment : BaseFragment<FragmentConfigRfDeviceBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_config_rf_device
    private val deviceList = arrayListOf<IoTDevice>()
    private var scanningTime = 60
    private val deviceMap = hashMapOf<String, IoTPairedZigbeeDevice>()
    private var device: IoTPairedRfDevice?= null
    private var gatewayId = ""
    private var deviceSubType = -1
    private val devType = IoTDeviceType.USB_DONGLE
    private lateinit var deviceSpinnerAdapter: DeviceSpinnerAdapter
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
                toolbar.txtTitle.text = resources.getString(R.string.add_rf_device)
                spinnerGroup.adapter = groupSpinnerAdapter
                binding.btnStartScan.visibility = View.GONE
            }
        }
    }

    override fun initAction() {
        super.initAction()
        binding.btnStartScan.setOnClickListener {
            startScan()
        }
        binding.spinnerHub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.btnStartScan.visibility = View.VISIBLE
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.btnStartScan.visibility = View.GONE
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            dialogLoading.show()
            SmartSdk.configRfDeviceHandler().checkNetworkAvailable(object : CheckDeviceAvailableCallback {
                override fun onDeviceAvailable(p0: String?) {
                    val device = SmartSdk.deviceHandler().get(p0)
                    deviceList.add(device)
                }
            })
            delay(5000)
            dialogLoading.dismiss()
            SmartSdk.configRfDeviceHandler().cancelCheckNetworkAvailable()
            deviceSpinnerAdapter = DeviceSpinnerAdapter(requireContext(), deviceList)
            binding.spinnerHub.adapter = deviceSpinnerAdapter
            binding.btnStartScan.visibility = if (deviceList.isNotEmpty()) View.VISIBLE else View.GONE

        }
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
        SmartSdk.configRfDeviceHandler().startPairingRf(
            gatewayId,
            scanningTime,
            devType,
            object : PairRfDeviceCallback {
                override fun onPairedDevice(p0: IoTPairedRfDevice?) {
                    device = p0
                    binding.edtDeviceName.setText(p0!!.name)
                    binding.lnDevice.visibility = View.VISIBLE
                    stopDiscovery()
                }

            }
        )
        CoroutineScope(Dispatchers.Default).launch {
            delay(10000)
            stopDiscovery()
            if(device == null) {
                showNoti(R.string.no_available_device)
            }
        }
    }
    private fun stopDiscovery() {
        SmartSdk.configRfDeviceHandler().stopPairingRf(gatewayId)
    }
    private fun setUp() {
        device?.let {
            SmartSdk.configRfDeviceHandler().addRfDevice(
                gatewayId,
                binding.edtDeviceName.text.toString(),
                (binding.spinnerGroup.selectedItem as IoTGroup).uuid,
                it,
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