package com.example.rogosample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.rogosample.`object`.Command
import com.example.rogosample.R
import rogo.iot.module.rogocore.basesdk.define.IoTAttribute
import rogo.iot.module.rogocore.basesdk.define.IoTCmdConst

class CommandSpinnerAdapter(context: Context, cmdList: List<Command>): ArrayAdapter<Command>(context, 0, cmdList) {

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
            textViewName.text = context.getString(it.cmdName)
        }
        return convertedView
    }
}