package com.example.rogosample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.rogosample.R
import com.example.rogosample.base.LocationType
import rogo.iot.module.rogocore.sdk.define.IoTGroupType

class GroupTypeAdapter(context: Context, groupTypeList: List<IoTGroupType>): ArrayAdapter<IoTGroupType>(context, 0, groupTypeList) {
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
            textViewName.text = it.name
        }
        return convertedView
    }
}