package com.pips.wikipediatest.ds.api

import com.pips.wikipediatest.model.QueryResponse
import io.reactivex.Single


internal object ApiDataSource {
    private val wikiApiService: WikiApiService = retrofitObject.create(WikiApiService::class.java)

    fun getArticles(title: String): Single<QueryResponse> = wikiApiService.getArticles(action_query,
            formatversion_2,
            generator_prefixsearch,
            gpslimit_20,
            prop_pageimagesPageterms,
            piprop_thumbnail,
            pithumbsize_150,
            pilimit_10,
            redirects,
            wbptterms_description,
            format_json,
            title)
}