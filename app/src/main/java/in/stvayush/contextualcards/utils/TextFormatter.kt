package `in`.stvayush.contextualcards.utils

import `in`.stvayush.contextualcards.models.FormattedText
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

object TextFormatter {
    fun TextView.format(formattedText: FormattedText?, plainText: String?) {
        this.text = if (formattedText == null) {
            plainText
        } else {
            val spannableStringBuilder = SpannableStringBuilder()
            for (index: Int in formattedText.entityList.indices) {
                val entity = formattedText.entityList[index]
                val color = Color.parseColor(formattedText.entityList[index].color)
                val spannableString = SpannableString("${entity.text} ") // deliberate space
                spannableString.setSpan(
                    ForegroundColorSpan(color),
                    0,
                    entity.text.length,
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                )
                spannableStringBuilder.append(spannableString)
            }
            if (spannableStringBuilder.isEmpty()) plainText else spannableStringBuilder
        }
    }
}
