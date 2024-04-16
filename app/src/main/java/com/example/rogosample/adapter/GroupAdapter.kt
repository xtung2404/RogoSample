package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemFunctionBinding
import com.example.rogosample.databinding.LayoutItemLocationBinding
import rogo.iot.module.rogocore.sdk.entity.IoTGroup

class GroupAdapter: ListAdapter<IoTGroup, GroupAdapter.GroupViewHolder>(object: DiffUtil.ItemCallback<IoTGroup>() {
    override fun areItemsTheSame(oldItem: IoTGroup, newItem: IoTGroup): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: IoTGroup, newItem: IoTGroup): Boolean {
        return oldItem == newItem
    }

}) {
    inner class GroupViewHolder(private val binding: LayoutItemLocationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(group: IoTGroup) {
            binding.txtName.text = group.label
            binding.txtType.text = group.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GroupViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}