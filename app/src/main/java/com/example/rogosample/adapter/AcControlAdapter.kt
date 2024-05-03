package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.`object`.AcControlItem
import com.example.rogosample.databinding.LayoutItemCheckboxBinding

class AcControlAdapter
    (private val isChecked: (Pair<Int, Boolean>) -> Unit): ListAdapter<AcControlItem, AcControlAdapter.AcControlViewHolder>(
    object : DiffUtil.ItemCallback<AcControlItem>() {
        override fun areItemsTheSame(oldItem: AcControlItem, newItem: AcControlItem): Boolean {
            return oldItem.itemId == newItem.itemId && oldItem.isChecked == newItem.isChecked
        }

        override fun areContentsTheSame(oldItem: AcControlItem, newItem: AcControlItem): Boolean {
            return oldItem.itemId == newItem.itemId && oldItem.isChecked == newItem.isChecked
        }
    }
) {
    inner class AcControlViewHolder(private val binding: LayoutItemCheckboxBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: AcControlItem) {
            binding.apply {
                txtIrCode.text = root.resources.getString(item.itemName)
                cbCode.isChecked = item.isChecked == true
                cbCode.setOnCheckedChangeListener { _, p1 ->
                    isChecked.invoke(
                        Pair(
                            item.itemId,
                            p1
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcControlViewHolder {
        val inflater = LayoutItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AcControlViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: AcControlViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}