package com.pips.wikipediatest.ds

import android.arch.lifecycle.LiveData
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.model.QueryResponse


object SearchDs : LiveData<List<Page>>() {
    private val baseUrl = "https://en.wikipedia.org/w/api.php"
    private val pagesQueryList = mutableListOf(
            "action" to "query",
            "formatversion" to 2,
            "generator" to "prefixsearch",
            "gpslimit" to 10,
            "prop" to "pageimages|pageterms",
            "piprop" to "thumbnail",
            "pithumbsize" to 50,
            "pilimit" to 10,
            "redirects" to "",
            "wbptterms" to "description",
            "format" to "json"
    )

    fun getArticles(title: String) {
        pagesQueryList.add("gpssearch" to title)

        baseUrl.httpGet(pagesQueryList).responseObject<QueryResponse> { _, _, result -> value = result.get().query?.pages }
    }
}