package com.pips.wikipediatest.ui.activity

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.pips.wikipediatest.R
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.ui.adapter.PageAdapter
import com.pips.wikipediatest.ui.base.BaseActivity
import com.pips.wikipediatest.ui.dialog.buildPageDialog
import com.pips.wikipediatest.util.*
import com.pips.wikipediatest.vm.SearchVm
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.textChangedListener


class SearchActivity : BaseActivity() {

    lateinit var emptyView: TextView
    lateinit var startView: ImageView
    lateinit var fullView: RecyclerView

    lateinit var searchVm: SearchVm

    override fun buildAnkoLayout() {
        verticalLayout {
            padding = dip(16)
            gravity = Gravity.CENTER

            editText {
                hint = getString(R.string.search_hint)
                textChangedListener { onTextChanged { charSequence, _, _, _ -> searchVm.getArticles(charSequence.toString()) } }
            }
            emptyView = textView(R.string.empty_searching) { centerHorizontalGravity() }.lparams(matchParent, matchParent)
            startView = imageView(R.drawable.ic_wikipedia).lparams(dip(100), dip(100), weight = 1F)
            fullView = recyclerView {
                val llManager = LinearLayoutManager(this@SearchActivity)
                layoutManager = llManager
                setHasFixedSize(true)
                addDivider(llManager)
            }.lparams(matchParent, matchParent)
        }
    }

    override fun onCreateActivity() {
        setStartView()

        searchVm = getViewModel()
        searchVm.searchResultMld.observe(this, Observer { pages ->
            when {
                pages == null -> setStartView()
                pages.isEmpty() -> setStartView()
                else -> {
                    setFullView()
                    setRvAdapter(pages)
                }
            }
        })
    }

    private fun setRvAdapter(pages: List<Page>) {
        fullView.adapter = PageAdapter(pages, onItemClick = { page -> showPageDetails(page) })
    }

    private fun showPageDetails(page: Page) = buildPageDialog(page).show()

    private fun setEmptyView() {
        emptyView.visible()
        startView.gone()
        fullView.gone()
    }

    private fun setStartView() {
        emptyView.gone()
        startView.visible()
        fullView.gone()
    }

    private fun setFullView() {
        emptyView.gone()
        startView.gone()
        fullView.visible()
    }
}