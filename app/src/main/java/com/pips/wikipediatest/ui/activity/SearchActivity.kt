package com.pips.wikipediatest.ui.activity

import android.animation.LayoutTransition
import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.pips.wikipediatest.R
import com.pips.wikipediatest.lifecycleObserver.RxEtSearchObserver
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.ui.adapter.PageAdapter
import com.pips.wikipediatest.ui.base.BaseActivity
import com.pips.wikipediatest.ui.dialog.buildPageDialog
import com.pips.wikipediatest.util.*
import com.pips.wikipediatest.vm.SearchVm
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class SearchActivity : BaseActivity() {

    private lateinit var emptyView: TextView
    private lateinit var startView: ImageView
    private lateinit var fullView: RecyclerView
    private lateinit var errorView: TextView
    private lateinit var searchVm: SearchVm
    private lateinit var etSearch: EditText

    override fun buildAnkoLayout() {
        verticalLayout {
            layoutTransition = LayoutTransition()
            padding = dip(16)
            gravity = Gravity.CENTER
            etSearch = editText {
                hint = getString(R.string.search_hint)
                singleLine = true
            }
            emptyView = textView(R.string.empty_searching) { centerHorizontalGravity() }.lparams(matchParent, matchParent)
            startView = imageView(R.drawable.ic_wikipedia).lparams(dip(100), dip(100), weight = 1F)
            fullView = recyclerView {
                val llManager = LinearLayoutManager(this@SearchActivity)
                layoutManager = llManager
                setHasFixedSize(true)
                addDivider(llManager)
            }.lparams(matchParent, matchParent)
            errorView = textView { centerHorizontalGravity() }.lparams(matchParent, matchParent)
        }
    }

    override fun onCreateActivity() {
        setStartView()
        createSearchVm()
        addRxSearchObserver()
    }

    private fun createSearchVm(){
        searchVm = getViewModel<SearchVm>().apply {
            searchResultLd.observe(this@SearchActivity, Observer {
                if (etSearch.text.toString().isBlank()) setStartView()
                else {
                    it?.let { outcome ->
                        when {
                            outcome.state.isError -> {
                                setErrorView()
                                displayErrorMsg(outcome.errorMsg)
                            }
                            outcome.state.isSuccess -> {
                                if (outcome.data == null) setEmptyView()
                                else {
                                    setFullView()
                                    setRvAdapter(outcome.data)
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    private fun addRxSearchObserver(){
        lifecycle.addObserver(RxEtSearchObserver(etSearch, searchVm))
    }

    private fun setRvAdapter(pages: List<Page>) {
        fullView.adapter = PageAdapter(pages, onItemClick = { page -> showPageDetails(page) })
    }

    private fun showPageDetails(page: Page) = buildPageDialog(page).show()

    private fun setEmptyView() {
        emptyView.visible()
        startView.gone()
        fullView.gone()
        errorView.gone()
    }

    private fun setStartView() {
        emptyView.gone()
        startView.visible()
        fullView.gone()
        errorView.gone()
    }

    private fun setFullView() {
        emptyView.gone()
        startView.gone()
        fullView.visible()
        errorView.gone()
    }

    private fun setErrorView() {
        emptyView.gone()
        startView.gone()
        fullView.gone()
        errorView.visible()
    }

    private fun displayErrorMsg(errorMsg: String?) {
        errorView.text = errorMsg ?: getString(R.string.error_msg_common)
    }
}