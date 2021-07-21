package `in`.stvayush.contextualcards.utils

import `in`.stvayush.contextualcards.models.Cta
import `in`.stvayush.contextualcards.utils.DeepLinkParser.processDeepLink
import android.graphics.Color
import android.widget.Button

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */

object CtaConfigurer {
    fun Button.configureCta(cta: Cta?) {
        cta?.let {
            apply {
                text = it.text
                setTextColor(Color.parseColor(it.textColor))
                setBackgroundColor(Color.parseColor(it.bgColor))
                setOnClickListener {
                    if (cta.otherUrl != null) {
                        processDeepLink(cta.otherUrl)
                    } else if (cta.url != null) {
                        processDeepLink(
                            cta.url
                        )
                    }
                }
            }
        }
    }
}
