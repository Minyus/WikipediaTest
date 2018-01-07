package com.pips.wikipediatest.model

import com.google.gson.annotations.SerializedName

data class QueryResponse(@SerializedName("query") var query: Query?)

data class Query(@SerializedName("pages") var pages: List<Page>)

data class Page(@SerializedName("pageid") var pageid: Int,
                @SerializedName("title") var title: String,
                @SerializedName("index") var index: Int,
                @SerializedName("thumbnail") var thumbnail: Thumbnail?,
                @SerializedName("terms") var terms: Terms?)


data class Terms(@SerializedName("description") var description: List<String>?)

data class Thumbnail(@SerializedName("source") var source: String?)
