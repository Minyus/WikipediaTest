package com.pips.wikipediatest.ds


val mainMobileWikiUrl = "https://en.m.wikipedia.org/wiki/"

val baseWikiUrlApi = "https://en.wikipedia.org/w/api.php"

val imageItemPageSize = 150

val searchLimitItems = 20

val pagesQueryList = mutableListOf(
        "action" to "query",
        "formatversion" to 2,
        "generator" to "prefixsearch",
        "gpslimit" to searchLimitItems,
        "prop" to "pageimages|pageterms",
        "piprop" to "thumbnail",
        "pithumbsize" to 150,
        "pilimit" to 10,
        "redirects" to "",
        "wbptterms" to "description",
        "format" to "json")