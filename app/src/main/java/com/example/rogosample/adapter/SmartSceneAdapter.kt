package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemFunctionBinding
import com.example.rogosample.databinding.LayoutItemLocationBinding
import rogo.iot.module.rogocore.sdk.entity.smart.IoTSmart

class SmartSceneAdapter(
    private val onItemClick: (String) -> Unit
): ListAdapter<IoTSmart, SmartSceneAdapter.SmartSceneViewHolder>(object : DiffUtil.ItemCallback<IoTSmart>() {
    override fun areItemsTheSame(oldItem: IoTSmart, newItem: IoTSmart): Boolean {
        return oldItem.label == newItem.label && oldItem.uuid == newItem.uuid

    }

    override fun areContentsTheSame(oldItem: IoTSmart, newItem: IoTSmart): Boolean {
        return oldItem.label == newItem.label && oldItem.uuid == newItem.uuid
    }

}) {
    inner class SmartSceneViewHolder(private val binding: LayoutItemFunctionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(ioTSmart: IoTSmart) {
            binding.txtFunction.text = ioTSmart.label
            itemView.setOnClickListener {
                onItemClick.invoke(ioTSmart.uuid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartSceneViewHolder {
        val inflater = LayoutItemFunctionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SmartSceneViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SmartSceneViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}