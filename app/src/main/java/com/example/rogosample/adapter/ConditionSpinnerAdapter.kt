package com.example.rogosample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rogosample.R
import com.example.rogosample.databinding.LayoutItemFunctionBinding
import com.example.rogosample.`object`.Command
import rogo.iot.module.rogocore.basesdk.define.IoTCondition

class ConditionSpinnerAdapter(context: Context, conditionList: List<Int>): ArrayAdapter<Int>(context, 0, conditionList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertedView = convertView
        if (convertedView == null) {
            convertedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_spinner, parent, false)
        }
        val textViewName: TextView = convertedView!!.findViewById(R.id.txt_function)
        getItem(position)?.let {
            when(it) {
                IoTCondition.ANY -> textViewName.text = "Any"
                IoTCondition.EQUAL -> textViewName.text = "="
                IoTCondition.LESS_THAN -> textViewName.text = "<"
                IoTCondition.LESS_EQUAL -> textViewName.text = "<="
                IoTCondition.GREATER_THAN -> textViewName.text = ">"
                IoTCondition.GREATER_EQUAL -> textViewName.text = ">="
                IoTCondition.BETWEEN -> textViewName.text = "In"

            }
        }
        return convertedView
    }
}