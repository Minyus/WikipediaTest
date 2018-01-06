package com.pips.wikipediatest.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.pips.wikipediatest.R
import com.pips.wikipediatest.ui.base.BaseActivity
import com.pips.wikipediatest.util.centerGravity
import com.pips.wikipediatest.util.gone
import com.pips.wikipediatest.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.textChangedListener


class SearchActivity : BaseActivity() {

    lateinit var emptyView: TextView
    lateinit var startView: ImageView
    lateinit var fullView: RecyclerView

    override fun buildAnkoLayout() {
        verticalLayout {
            padding = dip(16)
            gravity = Gravity.CENTER

            editText {
                hint = getString(R.string.search_hint)
                textChangedListener { afterTextChanged { } }
            }
            emptyView = textView(R.string.empty_searching) { centerGravity() }.lparams(matchParent, matchParent)
            startView = imageView(R.drawable.ic_wikipedia).lparams(dip(100), dip(100), weight = 1F)
            fullView = recyclerView {
                layoutManager = LinearLayoutManager(this@SearchActivity)
                setHasFixedSize(true)
            }
        }
    }

    override fun onCreateActivity() {
        setStartView()
    }

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