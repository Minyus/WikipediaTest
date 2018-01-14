package com.pips.wikipediatest.ds.api

import com.pips.wikipediatest.model.QueryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


internal interface WikiApiService {

    @GET("api.php")
    fun getArticles(@Query("action") action_query: String,
                    @Query("formatversion") formatversion_2: Int,
                    @Query("generator") generator_prefixsearch: String,
                    @Query("gpslimit") gpslimit_20: Int,
                    @Query("prop") prop_pageimagesPageterms: String,
                    @Query("piprop") piprop_thumbnail: String,
                    @Query("pithumbsize") pithumbsize_150: Int,
                    @Query("pilimit") pilimit_10: Int,
                    @Query("redirects") redirects: String,
                    @Query("wbptterms") wbptterms_description: String,
                    @Query("format") format_json: String,
                    @Query("gpssearch") title: String): Single<QueryResponse>
}