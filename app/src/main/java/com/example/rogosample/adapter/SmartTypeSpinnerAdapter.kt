package com.example.rogosample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.rogosample.R
import rogo.iot.module.rogocore.basesdk.define.IoTAutomationType

class SmartTypeSpinnerAdapter(context: Context, automationTypeList: List<Int>): ArrayAdapter<Int>(context, 0, automationTypeList) {

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
            textViewName.text = when(it) {
                IoTAutomationType.TYPE_STAIR_SWITCH -> context.getString(R.string.stair_switch)
                IoTAutomationType.TYPE_NOTIFICATION -> context.getString(R.string.notification)
                IoTAutomationType.TYPE_SMART_REVERSE_ON_OFF -> context.getString(R.string.self_reverse)
                else -> context.getString(R.string.advance)
            }

        }
        return convertedView
    }
}