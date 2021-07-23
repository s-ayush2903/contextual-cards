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
    /**
     * An extension function on a [TextView] which takes
     * @param [formattedText] which comprises of [Entities] which contain properties
     * that are supposed to be used for formatting the text to be rendered on screen
     * as a [TextView].
     * @param plainText is passed here as a fallbackText, in case if there arise any problem(s)
     * while parsing the [formattedText]
     *
     * The reason why this is an extension on [TextView] is that, [TextView] is the final thing
     * that is going to be displayed on the device rather [plainText] which is not
     * directly being rendered on the screen. So it seems more intuitive to make it an extension
     * on [TextView] rather [plainText]
     */
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
