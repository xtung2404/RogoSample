package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemSwitchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rogo.iot.module.rogocore.basesdk.ILogR
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.SmartSdkDeviceStateCallback
import rogo.iot.module.rogocore.sdk.entity.IoTDevice

class SwitchDeviceAdapter: ListAdapter<IoTDevice, SwitchDeviceAdapter.SwitchDeviceViewHolder>(
    object : DiffUtil.ItemCallback<IoTDevice>() {
        override fun areItemsTheSame(oldItem: IoTDevice, newItem: IoTDevice): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: IoTDevice, newItem: IoTDevice): Boolean {
            return false
        }

    }
) {
    inner class SwitchDeviceViewHolder(private val binding: LayoutItemSwitchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(ioTDevice: IoTDevice) {
            val switchElementAdapter by lazy {
                SwitchElementAdapter()
            }
            binding.apply {
                val map = hashMapOf<Int, Boolean>()
                txtDeviceName.text = ioTDevice.label
                rvSwitchButton.adapter = switchElementAdapter
                val smartSdkStateObjectCallback = object : SmartSdkDeviceStateCallback() {
                    override fun onDeviceStateUpdated(devId: String?) {

                    }

                    override fun onEventAttrStateChange(
                        devId: String?,
                        element: Int,
                        values: IntArray?
                    ) {
                        if(devId?.contentEquals(ioTDevice.uuid) == true) {
                            when(values?.toList()?.get(1)) {
                                0 -> {
                                    map[element] = false
                                }
                                1 -> {
                                    map[element] = true
                                }
                            }
                            switchElementAdapter.notifyDataSetChanged()
                            ILogR.D("SwitchDeviceAdapter", devId!!, element, values?.toList())
                        }
                    }

                    override fun onDeviceLogSensorUpdated(
                        devId: String?,
                        element: Int,
                        attrType: Int
                    ) {

                    }
                }
                SmartSdk.registerDeviceStateCallback(smartSdkStateObjectCallback)
                SmartSdk.stateHandler().pingDeviceState(ioTDevice.uuid)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    val state = SmartSdk.stateHandler().getObjState(ioTDevice.uuid)
                    ioTDevice.elementIds.forEach {
                        val isOn = state.isOn(it)
                        map[it] = isOn
                        ILogR.D("SwitchDeviceAdapter", it, isOn)
                    }
                    map.entries.sortedBy {
                        it.key
                    }
                    switchElementAdapter.submitList(map.entries.toList())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchDeviceViewHolder {
        val inflater = LayoutItemSwitchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SwitchDeviceViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SwitchDeviceViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}