package com.example.rogosample.UI.device.state

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.ElementSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentStateDeviceBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rogo.iot.module.platform.ILogR
import rogo.iot.module.platform.callback.SuccessRequestCallback
import rogo.iot.module.platform.define.IoTAttribute
import rogo.iot.module.platform.define.IoTDeviceType
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import java.util.Arrays

class StateDeviceFragment : BaseFragment<FragmentStateDeviceBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_state_device

    private var ioTDevice: IoTDevice? = null
    private lateinit var elementSpinnerAdapter: ElementSpinnerAdapter
    private val elementMap = hashMapOf<Int, String>()

    override fun initVariable() {
        super.initVariable()
        binding.apply {
            arguments?.let {
                ioTDevice = SmartSdk.deviceHandler().get(it.getString("uuid"))
            }
            ioTDevice?.let {
                ILogR.D("ControlDeviceFragment", it.productId, it.devType, Arrays.toString(it.features))
                it.elementInfos.entries.forEach {
                    ILogR.D("ControlDeviceFragment", it.key, it.value.label, it.value.devType, Arrays.toString(it.value.attrs))
                }
                ILogR.D("ControlDeviceFragment", "PRODUCT_MODEL", Gson().toJson(SmartSdk.getProductModel(it.productId)))
                SmartSdk.stateHandler().pingDeviceState(
                    it.uuid,
                    object : SuccessRequestCallback {
                        override fun onSuccess() {
                            ILogR.D("Ping state", "onSuccess")
                            CoroutineScope(Dispatchers.Main).launch {
                                val currentState = SmartSdk.stateHandler().getObjState(it.uuid)
                                if (it.containtFeature(IoTAttribute.EVT_ONLINE_STATUS)) {
                                    lnOnline.visibility = View.VISIBLE
                                    txtOnline.text = if (currentState.isOn) "true" else "false"
                                }
                                if (it.containtFeature(IoTAttribute.EVT_BATTERY)) {
                                    lnCurrentBattery.visibility = View.VISIBLE
                                    txtCurrentBattery.text = currentState.battery.toString()
                                }
                                if (it.containtFeature(IoTAttribute.EVT_LUX)) {
                                    lnCurrentLux.visibility = View.VISIBLE
                                    txtCurrentLux.text = currentState.lux.toString()
                                }
                                if (it.containtFeature(IoTAttribute.INFO_CURRENT_ISSUES_STATUS)) {
                                    lnHasIssues.visibility = View.VISIBLE
                                    txtHasIssues.text = if (currentState.isHasIssues) "true" else "false"
                                }
                                if (it.containtFeature(IoTAttribute.EVT_TEMP)) {
                                    lnCurrentTemp.visibility = View.VISIBLE
                                    txtCurrentTemp.text = currentState.temp.toString()
                                }
                            }
                        }

                        override fun onFailure(p0: Int, p1: String?) {
                            ILogR.D("Ping state", "onFailure", p0, p1)
                        }

                    })
            }
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.control_device)
            ioTDevice?.let { it ->
                txtDeviceName.text = it.label
                elementMap.clear()
                it.elementInfos.forEach { entry ->
                    if (entry.value.label.isNullOrEmpty()) {
                        elementMap[entry.key] =
                            "Nút ${it.getIndexElement(entry.key)}"
                    } else {
                        elementMap[entry.key] = entry.value.label
                    }
                }
                elementSpinnerAdapter =
                    ElementSpinnerAdapter(requireContext(), elementMap.entries.toList())
                spinnerElement.adapter = elementSpinnerAdapter
                if(it.elementInfos.isEmpty()) {
                    lnElement.visibility = View.GONE
                } else {
                    lnElement.visibility = View.VISIBLE
                }
            }
        }
    }
}