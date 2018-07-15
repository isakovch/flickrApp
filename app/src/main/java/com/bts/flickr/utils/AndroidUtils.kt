package com.bts.flickr.utils

import android.content.Context
import android.widget.Toast

object AndroidUtils {

    fun showShortToast(context: Context, msg: String) {
        showToast(context, msg, Toast.LENGTH_SHORT)
    }

    fun showLongToast(context: Context, msg: String) {
        showToast(context, msg, Toast.LENGTH_LONG)
    }

    private fun showToast(context: Context, msg: String, length: Int) {
        Toast.makeText(context, msg, length).show()
    }

}