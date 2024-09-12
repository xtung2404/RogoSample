package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemCheckboxBinding

class ElementSelectionAdapter(
    private val isChecked: (Pair<Int, Boolean>) -> Unit
): ListAdapter<Map.Entry<Int, String>, ElementSelectionAdapter.ElementSelectionViewHolder>(object: DiffUtil.ItemCallback<Map.Entry<Int, String>>() {
    override fun areItemsTheSame(
        oldItem: Map.Entry<Int, String>,
        newItem: Map.Entry<Int, String>
    ): Boolean {
        return oldItem.key == newItem.key && oldItem.value == newItem.value
    }

    override fun areContentsTheSame(
        oldItem: Map.Entry<Int, String>,
        newItem: Map.Entry<Int, String>
    ): Boolean {
        return oldItem.key == newItem.key && oldItem.value == newItem.value
    }


}) {
    inner class ElementSelectionViewHolder(private val binding: LayoutItemCheckboxBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(element: Map.Entry<Int, String>) {
            binding.txtIrCode.text = element.value
            binding.cbCode.setOnCheckedChangeListener(object : OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    isChecked.invoke(Pair(element.key, p1))
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementSelectionViewHolder {
        val inflater = LayoutItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ElementSelectionViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ElementSelectionViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}