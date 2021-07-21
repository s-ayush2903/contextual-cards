package `in`.stvayush.contextualcards.views.viewholders

import `in`.stvayush.contextualcards.ContextualCardsApplication
import `in`.stvayush.contextualcards.databinding.LayoutImageCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardBinding
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.utils.ImageLoader.loadImage
import `in`.stvayush.contextualcards.utils.TextFormatter.format
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

class CardViewHolder(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    inner class SmallCardViewHolder(private val smallCardBinding: LayoutSmallCardBinding) {
        fun bindSmallCard(card: Card) {
            with(smallCardBinding) {
                with(card) {
                    smallCardTitle.format(formattedTitle, title)
                    smallCardDescription.format(formattedDescription, description)
                    icon?.imgUrl?.let { smallCardIcon.loadImage(it) }
                    url?.let {
                        smallCardView.setOnClickListener {
                            initializeAction(this.url)
                        }
                    }
                }
            }
        }
    }

    inner class ImageCardViewHolder(private val imageCardBinding: LayoutImageCardBinding) {
        fun bindImageCard(card: Card) {
            with(imageCardBinding) {
                card.bgImage?.imgUrl?.let { imageCard.loadImage(it) }
                imageCard.setOnClickListener {
                    card.url?.let { initializeAction(it) }
                }
            }
        }
    }


    // FIXME: 22/7/21 Move this to Utils package
    companion object {
        private fun initializeAction(url: String): Boolean {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            ContextualCardsApplication.getContext().startActivity(intent)
            return true
        }

    }

}
