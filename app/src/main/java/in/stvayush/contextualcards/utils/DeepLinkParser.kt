package `in`.stvayush.contextualcards.utils

import android.content.Intent
import android.net.Uri
import `in`.stvayush.contextualcards.ContextualCardsApplication

/** Authored by Ayush Shrivastava on 22/7/21 */

/**
 * An object which handles all the deepLinks in the codebase, it takes the Link as a string and
 * creates a new [Intent] obtaining the [Context] from [ContextualCardsApplication] to start the
 * created [Intent]
 */
object DeepLinkParser {
  fun processDeepLink(deepLinkUrl: String): Boolean {
    val intent =
      Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(deepLinkUrl)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
      }
    ContextualCardsApplication.getContext().startActivity(intent)
    return true
  }
}
