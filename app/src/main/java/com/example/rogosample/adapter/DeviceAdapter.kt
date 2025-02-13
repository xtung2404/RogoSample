package com.example.rogosample.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.R
import com.example.rogosample.databinding.LayoutItemDeviceBinding
import com.example.rogosample.databinding.LayoutItemFunctionBinding
import com.example.rogosample.databinding.LayoutItemLocationBinding
import rogo.iot.module.platform.define.IoTAttribute
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTDevice

class DeviceAdapter(
    private val onControlSelected: (String) -> Unit,
    private val onStateSelected: (String) -> Unit,
    ): ListAdapter<IoTDevice, DeviceAdapter.DeviceViewHolder>(object: DiffUtil.ItemCallback<IoTDevice>() {
    override fun areItemsTheSame(oldItem: IoTDevice, newItem: IoTDevice): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: IoTDevice, newItem: IoTDevice): Boolean {
        return oldItem == newItem
    }

}) {
    inner class DeviceViewHolder(private val binding: LayoutItemDeviceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(device: IoTDevice) {
            binding.txtFunction.text = device.label
            binding.txtState.setOnClickListener {
                onStateSelected.invoke(device.uuid)
            }
            binding.txtControl.setOnClickListener {
                onControlSelected.invoke(device.uuid)
            }
//            if(device.groupId.isNullOrEmpty()) {
//                binding.txtType.text = binding.root.resources.getString(R.string.no_group)
//            } else
//            binding.txtType.text = SmartSdk.groupHandler().get(device.groupId).label
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutItemDeviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeviceViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}