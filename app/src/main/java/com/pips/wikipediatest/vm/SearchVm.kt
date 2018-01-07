package com.pips.wikipediatest.vm

import android.arch.lifecycle.ViewModel
import com.pips.wikipediatest.ds.SearchDs


class SearchVm : ViewModel() {

    val searchResultMld = SearchDs

    fun getArticles(text: String) {
        text.takeIf { it.isNotBlank() }?.let { SearchDs.getArticles(text) }
    }
}