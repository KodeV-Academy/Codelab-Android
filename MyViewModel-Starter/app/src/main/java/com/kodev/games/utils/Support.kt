package com.kodev.games.utils

import android.os.Build
import android.text.Html
import android.widget.TextView

object Support {
    fun TextView.convertHtmlTagToText(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        } else
            this.text = Html.fromHtml(text)
    }
}
