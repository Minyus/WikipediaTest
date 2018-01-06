package com.pips.wikipediatest.ui.dialog

import android.view.Gravity
import com.pips.wikipediatest.ui.base.BaseActivity
import org.jetbrains.anko.*

private val pbSize = 50

fun BaseActivity.buildLoadingDialog() = alert {
    isCancelable = false
    customView {
        verticalLayout {
            gravity = Gravity.CENTER
            progressBar { isIndeterminate = true }.lparams(dip(pbSize), dip(pbSize))
        }
    }
}

