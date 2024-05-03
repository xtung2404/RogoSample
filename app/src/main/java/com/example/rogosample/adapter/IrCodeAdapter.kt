package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.databinding.LayoutItemCheckboxBinding
import com.example.rogosample.`object`.IrCode

class IrCodeAdapter(
) : ListAdapter<IrCode, IrCodeAdapter.IrCodeViewHolder>(object : DiffUtil.ItemCallback<IrCode>() {
    override fun areItemsTheSame(oldItem: IrCode, newItem: IrCode): Boolean {
        return oldItem.irId == newItem.irId && oldItem.isChecked == newItem.isChecked && oldItem.irName == newItem.irName
    }

    override fun areContentsTheSame(oldItem: IrCode, newItem: IrCode): Boolean {
        return oldItem.irId == newItem.irId && oldItem.isChecked == newItem.isChecked && oldItem.irName == newItem.irName
    }

}) {
    inner class IrCodeViewHolder(private val binding: LayoutItemCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(irCode: IrCode) {
            binding.txtIrCode.text = binding.root.context.getString(irCode.irName)
            binding.cbCode.isChecked = irCode.isChecked == true
            binding.cbCode.setOnCheckedChangeListener { _, p1 -> irCode.isChecked = p1 }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IrCodeViewHolder {
        val inflater = LayoutItemCheckboxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IrCodeViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: IrCodeViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}