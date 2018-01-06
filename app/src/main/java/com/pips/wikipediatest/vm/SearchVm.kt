package com.pips.wikipediatest.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pips.wikipediatest.util.Result


class SearchVm : ViewModel() {

    val searchResultMld = MutableLiveData<Result>()

    fun getArticles() {

    }
}