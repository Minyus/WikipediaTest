package com.pips.wikipediatest.ds

import android.arch.lifecycle.LiveData
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.model.QueryResponse
import com.pips.wikipediatest.util.Content
import com.pips.wikipediatest.util.Error
import com.pips.wikipediatest.util.Outcome


object SearchDs : LiveData<Outcome>() {

    fun getArticles(title: String) {
        pagesQueryList.add("gpssearch" to title)
        baseWikiUrlApi.httpGet(pagesQueryList).responseObject<QueryResponse> { _, _, result ->
            when (result) {
                is Result.Success -> result.get().query?.pages?.let { value = Content(it) }
                        ?: let { value = Content<List<Page>>(emptyList()) }
                is Result.Failure -> value = Error(result.error.exception.message)
            }
            pagesQueryList.remove("gpssearch" to title)
        }
    }
}