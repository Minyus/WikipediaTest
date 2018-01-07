package com.pips.wikipediatest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.pips.wikipediatest.R
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.util.inflate
import com.pips.wikipediatest.util.loadImage
import kotlinx.android.synthetic.main.item_page.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class PageAdapter(val pages: List<Page>, val onItemClick: (Page) -> Unit) : RecyclerView.Adapter<PageAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pages[position], onItemClick)

    override fun getItemCount() = pages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.item_page))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(page: Page, onItemClick: (Page) -> Unit) = with(itemView) {
            onClick { onItemClick(page) }
            imvPage.loadImage(page.thumbnail?.source)
            tvItemPageTitle.text = page.title
            tvItemPageDescription.text = page.terms?.description?.get(0)
        }

    }
}