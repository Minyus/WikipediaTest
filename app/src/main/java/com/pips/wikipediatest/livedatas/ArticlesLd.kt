package com.pips.wikipediatest.livedatas

import android.arch.lifecycle.LiveData
import com.pips.wikipediatest.ds.Repository
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.model.QueryResponse
import com.pips.wikipediatest.util.Outcome
import com.pips.wikipediatest.util.State
import com.pips.wikipediatest.util.subscribeIoObserveMainThread
import io.reactivex.disposables.Disposable


object ArticlesLd : LiveData<Outcome<List<Page>>>() {

    private var disposable: Disposable? = null

    fun getArticles(title: String) {
        disposable = Repository
                .getArticles(title)
                .subscribeIoObserveMainThread()
                .subscribe(
                        { result -> value = Outcome(state = State(isSuccess = true), data = (result as QueryResponse).query?.pages) },
                        { error -> value = Outcome(state = State(isError = true), errorMsg = error.message) }
                )
    }

    override fun onInactive() {
        disposable?.dispose()
    }
}