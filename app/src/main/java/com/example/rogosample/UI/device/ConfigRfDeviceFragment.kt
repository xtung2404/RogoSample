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
import rogo.iot.module.platform.ILogR
import rogo.iot.module.platform.callback.RequestCallback
import rogo.iot.module.platform.define.IoTDeviceSubType
import rogo.iot.module.platform.define.IoTDeviceType
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.CheckDeviceAvailableCallback
import rogo.iot.module.rogocore.sdk.callback.CheckListDeviceAvailableCallback
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
//    private val devType = IoTDeviceType.
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
            SmartSdk.configRfDeviceHandler().checkGatewayAvailable (5, object : CheckListDeviceAvailableCallback {
                override fun onDeviceAvailable(p0: MutableCollection<IoTDevice>?) {
                    ILogR.D("ConfigRfDeviceFragment", "CENTRAL_LIST", p0?.size)
                    CoroutineScope(Dispatchers.Main).launch {
                        p0?.let {
                            dialogLoading.dismiss()
                            deviceList.addAll(it)
                            deviceSpinnerAdapter = DeviceSpinnerAdapter(requireContext(), deviceList)
                            binding.spinnerHub.adapter = deviceSpinnerAdapter
                            binding.btnStartScan.visibility = if (deviceList.isNotEmpty()) View.VISIBLE else View.GONE
                        }
                    }
                }
            })
//            SmartSdk.configRfDeviceHandler().cancelCheckRfGatewayAvailable()


        }
        binding.btnAddDevice.setOnClickListener {
            setUp()
        }
    }
    private fun startScan() {
        dialogLoading.show()
        stopDiscovery()
        gatewayId = (binding.spinnerHub.selectedItem as IoTDevice).uuid
        deviceMap.clear()
        SmartSdk.configRfDeviceHandler().startPairingRf(
            gatewayId,
            scanningTime,
            IoTDeviceType.ALL,
            object : PairRfDeviceCallback {
                override fun onPairedDevice(p0: IoTPairedRfDevice?) {
                    CoroutineScope(Dispatchers.Main).launch {
                        dialogLoading.dismiss()
                        p0?.let {
                            device = it
                            binding.edtDeviceName.setText(it.name)
                            binding.lnDevice.visibility = View.VISIBLE
//                            stopDiscovery()
                        }
                    }
                }

                override fun onNoDevicePaired() {
                    stopDiscovery()
                    dialogLoading.dismiss()
                    ILogR.D("No available")
                }

            }
        )
        CoroutineScope(Dispatchers.Main).launch {
            delay(10000)
            stopDiscovery()
            dialogLoading.dismiss()
            if(device == null) {
                showNoti(R.string.no_available_device)
            }
        }
    }
    private fun stopDiscovery() {
        SmartSdk.configRfDeviceHandler().stopPairingRf(gatewayId)
    }
    private fun setUp() {
        dialogLoading.show()
        device?.let {
            SmartSdk.configRfDeviceHandler().syncDeviceToCloud(
                gatewayId,
                binding.edtDeviceName.text.toString(),
                (binding.spinnerGroup.selectedItem as IoTGroup).uuid,
                it,
                object: RequestCallback<IoTDevice> {
                    override fun onFailure(errorCode: Int, message: String?) {
                        dialogLoading.dismiss()

                        message?.let {
                            showNoti(it)
                        }
                    }

                    override fun onSuccess(item: IoTDevice?) {
                        CoroutineScope(Dispatchers.Main).launch {
                            dialogLoading.dismiss()
                            showNoti(getString(R.string.connect_to, item!!.label))
                        }
                    }
                }
            )
        }
    }

}