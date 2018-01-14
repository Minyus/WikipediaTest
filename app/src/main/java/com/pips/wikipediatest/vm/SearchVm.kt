package com.pips.wikipediatest.vm

import android.arch.lifecycle.ViewModel
import com.pips.wikipediatest.livedatas.ArticlesLd


class SearchVm : ViewModel() {

    val searchResultMld = ArticlesLd

    fun getArticles(text: String) {
        searchResultMld.getArticles(text)
//        when {
//            charSequence.isNullOrBlank() -> setStartView()
//            isNetworkAvailable() -> {
//
//            }
//            else -> {
//                setErrorView()
//                displayErrorMsg(getString(R.string.error_no_internet))
//            }
//        }
    }
}