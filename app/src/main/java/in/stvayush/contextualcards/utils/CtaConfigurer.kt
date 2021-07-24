package `in`.stvayush.contextualcards.utils

import android.graphics.Color
import android.widget.Button
import `in`.stvayush.contextualcards.models.Cta
import `in`.stvayush.contextualcards.utils.DeepLinkParser.processDeepLink

/** Authored by Ayush Shrivastava on 22/7/21 */

/**
 * An object containing an extension function on [Button] for Configuring [Cta] (Call To Action) on
 * it, the function is on button and takes a [Cta] and uses its properties to set the UI of button
 * and install link(s) in it (if available) to trigger the intended Action
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
            processDeepLink(cta.url)
          }
        }
      }
    }
  }
}
