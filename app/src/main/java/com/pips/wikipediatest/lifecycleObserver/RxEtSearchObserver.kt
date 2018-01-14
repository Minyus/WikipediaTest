package com.pips.wikipediatest.lifecycleObserver

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import com.pips.wikipediatest.vm.SearchVm
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class RxEtSearchObserver(etSearch: EditText, private val searchVm: SearchVm) : LifecycleObserver {

    private var textDisposable: Disposable? = null

    private val textObservable: Observable<String> = RxTextView.textChanges(etSearch)
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { it.toString() }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startEtTextListener() {
        textDisposable = textObservable.subscribe { searchVm.getArticles(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopEtTextListener() {
        textDisposable?.dispose()
    }

}