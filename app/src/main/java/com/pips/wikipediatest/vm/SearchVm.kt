package com.pips.wikipediatest.vm

import android.arch.lifecycle.ViewModel
import com.pips.wikipediatest.livedatas.ArticlesLd


class SearchVm : ViewModel() {

    val searchResultLd = ArticlesLd

    fun getArticles(text: String) = searchResultLd.getArticles(text)
}