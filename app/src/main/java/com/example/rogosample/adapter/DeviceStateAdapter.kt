package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.ItemDeviceStateBinding
import com.google.gson.Gson
import rogo.iot.module.rogocore.sdk.entity.IoTObjState

class DeviceStateAdapter: ListAdapter<IoTObjState, DeviceStateAdapter.DeviceStateViewHolder> (
    object : DiffUtil.ItemCallback<IoTObjState>() {
        override fun areItemsTheSame(oldItem: IoTObjState, newItem: IoTObjState): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: IoTObjState, newItem: IoTObjState): Boolean {
            return false
        }
    }
) {
    inner class DeviceStateViewHolder(private val binding: ItemDeviceStateBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(state: IoTObjState) {
            binding.apply {
                txtDeviceLabel.text = state.label
                txtState.text = Gson().toJson(state)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceStateViewHolder {
        val inflater = ItemDeviceStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeviceStateViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: DeviceStateViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}