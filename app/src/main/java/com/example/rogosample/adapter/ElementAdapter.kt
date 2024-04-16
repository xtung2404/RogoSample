package com.example.rogosample.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.rogosample.databinding.LayoutItemEdttextBinding

class ElementAdapter(
    private val labelChange: (Pair<Int, String>) -> Unit) : ListAdapter<Map.Entry<Int, String>, ElementAdapter.ElementViewHolder>(object : DiffUtil.ItemCallback<Map.Entry<Int, String>>() {
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
    inner class ElementViewHolder(private val binding: LayoutItemEdttextBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(entry: Map.Entry<Int, String>) {
            binding.txtName.setText(entry.value)
            binding.txtName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        labelChange.invoke(Pair(entry.key, it.toString()))
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val inflater = LayoutItemEdttextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ElementViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }



}