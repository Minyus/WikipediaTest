package com.pips.wikipediatest.ui.dialog

import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import com.pips.wikipediatest.R
import com.pips.wikipediatest.ds.api.mainMobileWikiUrl
import com.pips.wikipediatest.model.Page
import com.pips.wikipediatest.ui.base.BaseActivity
import com.pips.wikipediatest.util.centerHorizontalGravity
import com.pips.wikipediatest.util.loadImage
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

val imageItemPageSize = 150

fun BaseActivity.buildPageDialog(page: Page) = alert {
    customView {
        verticalLayout {
            padding = dip(16)
            imageView(R.drawable.ic_wikipedia) { loadImage(page.thumbnail?.source) }
                    .lparams(dip(imageItemPageSize), dip(imageItemPageSize)) { gravity = Gravity.CENTER_HORIZONTAL }
            textView {
                centerHorizontalGravity()
                textSize = sp(10).toFloat()
                text = page.title
                setTypeface(typeface, Typeface.BOLD)
            }
            textView(page.terms?.description?.get(0) ?: "") {
                centerHorizontalGravity()
                inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            }.lparams(width = matchParent, height = wrapContent)
            button(getString(R.string.go)) { onClick { browse(mainMobileWikiUrl + page.title) } }
            cancelButton { }
        }
    }
}