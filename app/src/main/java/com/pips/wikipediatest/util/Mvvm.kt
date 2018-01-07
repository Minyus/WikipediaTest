package com.pips.wikipediatest.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity

sealed class Outcome
class Content<out T>(val data: T? = null) : Outcome()
class Error(val errorMsg: String? = null) : Outcome()


inline fun <reified VM : ViewModel> AppCompatActivity.getViewModel() = ViewModelProviders.of(this).get(VM::class.java)


fun Context.isNetworkAvailable() =
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnectedOrConnecting ?: false
