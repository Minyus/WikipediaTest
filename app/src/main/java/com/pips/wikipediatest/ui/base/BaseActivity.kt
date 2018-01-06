package com.pips.wikipediatest.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pips.wikipediatest.R
import com.pips.wikipediatest.ui.dialog.buildLoadingDialog
import org.jetbrains.anko.longToast


abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutResId: Int

    abstract fun buildAnkoLayout()

    abstract fun onCreateActivity()

    private var alertDialog: DialogInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        buildAnkoLayout()
        onCreateActivity()
    }

    fun showProgress() {
        if (alertDialog == null) alertDialog = buildLoadingDialog().run { show() }
    }

    fun hideProgress() = alertDialog?.cancel()

    fun showError(errorMsg: String?) {
        errorMsg?.let { longToast(it) } ?: longToast(R.string.error_default_msg)
    }
}