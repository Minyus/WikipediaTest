package com.pips.wikipediatest.util

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.pips.wikipediatest.ui.base.BaseActivity

sealed class Result

class Loading : Result()
class Content<out T>(val data: T? = null) : Result()
class Error(val errorMsg: String? = null) : Result()


inline fun <T> BaseActivity.addLceObserver(crossinline onContentLoaded: (T) -> Unit): Observer<Result> = Observer { result ->
    when (result) {
        is Loading -> showProgress()
        is Error -> {
            hideProgress()
            showError(result.errorMsg)
        }
        is Content<*> -> {
            hideProgress()
            (result.data as T).let { onContentLoaded(it) }
        }
    }
}

inline fun <T> noNullDataObserver(crossinline onContentLoaded: (T) -> Unit): Observer<T> = Observer { result ->
    result?.let { onContentLoaded(it) }
}

inline fun <reified VM : ViewModel> AppCompatActivity.getViewModel() = ViewModelProviders.of(this).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.getViewModel() = ViewModelProviders.of(this).get(VM::class.java)

fun MutableLiveData<Result>.setErrorValue(errorMsg: String?) {
    value = Error()
}

fun MutableLiveData<Result>.setLoadingValue() {
    value = Loading()
}

fun <T> MutableLiveData<Result>.setContentValue(data: T?) {
    value = Content(data)
}