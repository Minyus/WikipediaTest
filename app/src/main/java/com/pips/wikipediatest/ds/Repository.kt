package com.pips.wikipediatest.ds

import com.pips.wikipediatest.ds.api.ApiDataSource

object Repository {
    fun getArticles(title: String) = ApiDataSource.getArticles(title)
}

