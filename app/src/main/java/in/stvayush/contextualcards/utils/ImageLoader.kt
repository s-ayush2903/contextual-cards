package `in`.stvayush.contextualcards.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */

object ImageLoader {
    fun View.loadImage(url: String, highRes: Boolean? = false) {
        var res = 300
        if (highRes == true) res = 1600
        Glide.with(context)
            .load(url)
            .override(res, res)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    this@loadImage.background = resource
                }
            })
    }
}
