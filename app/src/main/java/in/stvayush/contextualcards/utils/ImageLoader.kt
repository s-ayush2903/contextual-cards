package `in`.stvayush.contextualcards.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/** Authored by Ayush Shrivastava on 22/7/21 */
object ImageLoader {

  /**
   * An extension function on the [View] which makes it more customized rather being only on
   * [ImageView]s, it takes 2 params,
   * @param [url], the source from where image is supposed to be fetched and
   * @param [highres], a boolean, false by default, but passed as true for VERY high resolution
   * images to get them rendered properly on the device, as we can't load the VERY high resolution
   * image directly, because it becomes too large to draw on the canvas and exceeds the normal size.
   * So we need to change the specs accordingly without degrading the image quality(noticeably)
   */
  fun View.loadImage(url: String, highRes: Boolean? = false) {
    var res = 300
    if (highRes == true) res = 1600
    Glide.with(context)
      .load(url)
      .override(res, res)
      .into(
        object : CustomTarget<Drawable>() {
          override fun onLoadCleared(placeholder: Drawable?) {}

          override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            this@loadImage.background = resource
          }
        }
      )
  }
}
