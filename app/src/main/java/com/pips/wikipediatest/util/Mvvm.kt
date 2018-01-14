package com.pips.wikipediatest.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity

data class Outcome<out T>(val state: State, val data: T? = null, val errorMsg: String? = null)

data class State(val isLoading: Boolean = false, val isError: Boolean = false, val isSuccess: Boolean = false)

inline fun <reified VM : ViewModel> AppCompatActivity.getViewModel() = ViewModelProviders.of(this).get(VM::class.java)

fun Context.isNetworkAvailable() =
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnectedOrConnecting ?: false
