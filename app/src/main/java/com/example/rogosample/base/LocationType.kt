package com.example.rogosample.base

import com.example.rogosample.R
import rogo.iot.module.rogocore.sdk.define.IoTLocationType

object LocationType {
    fun getLocationDesc(desc: String): Int {
        val locationType = try {  IoTLocationType.valueOf(desc)
        } catch (ex: Exception) {
            IoTLocationType.Underfine
        }
        return when(locationType) {
            IoTLocationType.Home -> R.string.home
            IoTLocationType.Apartment -> R.string.apartment
            IoTLocationType.Hotel -> R.string.hotel
            IoTLocationType.Motel -> R.string.motel
            IoTLocationType.Office -> R.string.office
            IoTLocationType.Other -> R.string.other
            IoTLocationType.Restaurant -> R.string.restaurant
            IoTLocationType.Homestay -> R.string.homestay
            else -> R.string.undefined
        }
    }

    fun getLocationDesc(type: IoTLocationType): Int {
        return when(type) {
            IoTLocationType.Home -> R.string.home
            IoTLocationType.Apartment -> R.string.apartment
            IoTLocationType.Hotel -> R.string.hotel
            IoTLocationType.Motel -> R.string.motel
            IoTLocationType.Office -> R.string.office
            IoTLocationType.Other -> R.string.other
            IoTLocationType.Restaurant -> R.string.restaurant
            IoTLocationType.Homestay -> R.string.homestay
            else -> R.string.undefined
        }
    }
}