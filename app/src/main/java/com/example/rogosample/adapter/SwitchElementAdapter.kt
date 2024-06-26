package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemCheckboxBinding

class SwitchElementAdapter: ListAdapter<Map.Entry<Int, Boolean>, SwitchElementAdapter.SwitchElementViewHolder>(
    object : DiffUtil.ItemCallback<Map.Entry<Int, Boolean>>() {
        override fun areItemsTheSame(
            oldItem: Map.Entry<Int, Boolean>,
            newItem: Map.Entry<Int, Boolean>
        ): Boolean {
            return oldItem.key == newItem.key && oldItem.value == newItem.value
        }

        override fun areContentsTheSame(
            oldItem: Map.Entry<Int, Boolean>,
            newItem: Map.Entry<Int, Boolean>
        ): Boolean {
            return oldItem.key == newItem.key && oldItem.value == newItem.value
        }


    }
) {
    inner class SwitchElementViewHolder(private val binding: LayoutItemCheckboxBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Map.Entry<Int, Boolean>) {
            binding.txtIrCode.text = item.key.toString()
            binding.cbCode.isChecked = item.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchElementViewHolder {
        val inflater = LayoutItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SwitchElementViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SwitchElementViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}