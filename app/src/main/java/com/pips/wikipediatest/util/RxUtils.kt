package com.pips.wikipediatest.util

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Single<*>.subscribeIoObserveMainThread(): Single<*> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


