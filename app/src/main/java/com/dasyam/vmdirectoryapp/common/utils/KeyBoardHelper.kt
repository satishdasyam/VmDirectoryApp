package com.dasyam.vmdirectoryapp.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyBoardHelper(private val activityContext: Context) {

    fun showKeyBoard(view: View) {
        val imm: InputMethodManager =
            activityContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyBoard(view: View) {
        val imm: InputMethodManager =
            activityContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}