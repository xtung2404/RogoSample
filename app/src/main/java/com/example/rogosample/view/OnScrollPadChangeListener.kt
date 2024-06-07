package com.example.colorbot

interface OnScrollPadChangeListener {
    fun onScroll(angle: Double, isPressed: Boolean)
    fun onStopScroll()
}