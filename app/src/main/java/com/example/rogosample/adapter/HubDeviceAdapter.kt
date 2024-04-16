package com.example.rogosample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.rogosample.R
import com.example.rogosample.base.LocationType
import rogo.iot.module.rogocore.sdk.entity.IoTDevice

class HubDeviceAdapter(context: Context, deviceMap: HashMap<IoTDevice, Boolean>): ArrayAdapter<Map.Entry<IoTDevice, Boolean>>(context, 0, deviceMap.entries.toList()) {
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
                .inflate(R.layout.layout_item_location, parent, false)
        }
        val txtName: TextView = convertedView!!.findViewById(R.id.txt_name)
        val txtType: TextView = convertedView!!.findViewById(R.id.txt_type)
        getItem(position)?.let {
            txtName.text = it.key.label
            txtType.text = when(it.value) {
                true -> {
                    context.getString(R.string.device_is_available)
                }
                else ->
                    context.getString(R.string.device_is_unvailable)
            }
        }
        return convertedView
    }
}