package com.example.rogosample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.`object`.Function
import com.example.rogosample.databinding.LayoutItemFunctionBinding

class FunctionAdapter(
    private val onItemClick: (Function) -> Unit
): ListAdapter<Function, FunctionAdapter.FunctionViewHolder>(object : DiffUtil.ItemCallback<Function>() {
    override fun areItemsTheSame(oldItem: Function, newItem: Function): Boolean {
        return oldItem.funcName == newItem.funcName
    }

    override fun areContentsTheSame(oldItem: Function, newItem: Function): Boolean {
            return oldItem == newItem
    }

}) {
    inner class FunctionViewHolder(private val binding: LayoutItemFunctionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(function: Function) {
            binding.txtFunction.text = binding.root.resources.getString(function.funcName)
            itemView.setOnClickListener {
                onItemClick.invoke(function)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionViewHolder {
        val inflater = LayoutItemFunctionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FunctionViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: FunctionViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}