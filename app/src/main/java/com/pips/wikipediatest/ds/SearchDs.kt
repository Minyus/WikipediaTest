package com.pips.wikipediatest.ds

import android.arch.lifecycle.LiveData
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.model.QueryResponse


object SearchDs : LiveData<List<Page>>() {

    fun getArticles(title: String) {
        when {
            title.isBlank() -> value = null
            else -> {
                pagesQueryList.add("gpssearch" to title)
                baseWikiUrlApi.httpGet(pagesQueryList).responseObject<QueryResponse> { _, _, result ->
                    result.get().query?.pages?.let { value = it } ?: let { value = emptyList() }
                    pagesQueryList.remove("gpssearch" to title)
                }
            }
        }
    }
}