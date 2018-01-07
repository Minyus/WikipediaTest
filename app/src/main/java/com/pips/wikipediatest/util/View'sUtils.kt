package com.pips.wikipediatest.util

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


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

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadImage(url: String?) {
    url?.let { Glide.with(this.context).load(it).into(this) }
}

fun RecyclerView.addDivider(llManager: LinearLayoutManager) {
    addItemDecoration(DividerItemDecoration(context, llManager.orientation))
}