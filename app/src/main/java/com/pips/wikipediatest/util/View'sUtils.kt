package com.pips.wikipediatest.util

import android.view.Gravity
import android.view.View
import android.widget.TextView


fun TextView.centerGravity() {
    gravity = Gravity.CENTER
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}