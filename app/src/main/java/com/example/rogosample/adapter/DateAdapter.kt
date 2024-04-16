package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.`object`.Date
import com.example.rogosample.R
import com.example.rogosample.databinding.LayoutItemDateBinding
import rogo.iot.module.rogocore.basesdk.ILogR

class DateAdapter(
    private val isChecked: (Pair<Int, Boolean>) -> Unit
): ListAdapter<Date, DateAdapter.DateViewHolder>(object : DiffUtil.ItemCallback<Date>() {
    override fun areItemsTheSame(oldItem: Date, newItem: Date): Boolean {
        return oldItem.isChecked == newItem.isChecked && oldItem.dateIndex == newItem.dateIndex
    }

    override fun areContentsTheSame(oldItem: Date, newItem: Date): Boolean {
        return oldItem.isChecked == newItem.isChecked && oldItem.dateIndex == newItem.dateIndex
    }
}) {
    inner class DateViewHolder(private val binding: LayoutItemDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(date: Date) {
            binding.txtDate.text = when(date.dateIndex) {
                0 -> binding.root.resources.getString(R.string.sunday)
                1 -> binding.root.resources.getString(R.string.monday)
                2 -> binding.root.resources.getString(R.string.tuesday)
                3 -> binding.root.resources.getString(R.string.wednesday)
                4 -> binding.root.resources.getString(R.string.thursday)
                5 -> binding.root.resources.getString(R.string.friday)
                else -> binding.root.resources.getString(R.string.saturday)
            }
            binding.cbDate.isChecked = date.isChecked == true
            itemView.setOnClickListener {
                binding.cbDate.isChecked = !binding.cbDate.isChecked
                ILogR.D("cbCheck", binding.cbDate.isChecked)
            }
            binding.cbDate.setOnCheckedChangeListener(object: OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    if(p1) {
                        binding.txtDate.background = binding.root.resources.getDrawable(R.drawable.bg_input_orange_10dp, null)
                    } else {
                        binding.txtDate.background = binding.root.resources.getDrawable(R.drawable.bg_input_black_10dp, null)
                    }
                    isChecked.invoke(Pair(date.dateIndex, p1))
                }

            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutItemDateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DateViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}