package `in`.stvayush.contextualcards.utils

import `in`.stvayush.contextualcards.ContextualCardsApplication
import android.content.Intent
import android.net.Uri

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */

object DeepLinkParser {
    fun processDeepLink(deepLinkUrl: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(deepLinkUrl)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        ContextualCardsApplication.getContext().startActivity(intent)
        return true
    }
}
