package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.`object`.ELement
import com.example.rogosample.databinding.LayoutItemCheckboxBinding

class ElementCheckAdapter(
    private val isChecked: (Pair<Int, Boolean>) -> Unit
) : ListAdapter<ELement, ElementCheckAdapter.ElementCheckViewHolder>(object :
    DiffUtil.ItemCallback<ELement>() {
    override fun areItemsTheSame(oldItem: ELement, newItem: ELement): Boolean {
        return oldItem.elmIndex == newItem.elmIndex && oldItem.elmName == newItem.elmName && oldItem.isChecked == newItem.isChecked
    }

    override fun areContentsTheSame(oldItem: ELement, newItem: ELement): Boolean {
        return oldItem.elmIndex == newItem.elmIndex && oldItem.elmName == newItem.elmName && oldItem.isChecked == newItem.isChecked
    }

}) {

    inner class ElementCheckViewHolder(private val binding: LayoutItemCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(element: ELement) {
            binding.txtIrCode.text = element.elmName
            binding.cbCode.isChecked = element.isChecked == true
            binding.cbCode.setOnCheckedChangeListener(object :
                CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    isChecked.invoke(Pair(element.elmIndex, p1))
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementCheckViewHolder {
        val inflater = LayoutItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ElementCheckViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ElementCheckViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}