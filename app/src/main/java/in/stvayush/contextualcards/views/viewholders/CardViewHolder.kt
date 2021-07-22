package `in`.stvayush.contextualcards.views.viewholders

import `in`.stvayush.contextualcards.databinding.*
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.utils.CtaConfigurer.configureCta
import `in`.stvayush.contextualcards.utils.DeepLinkParser.processDeepLink
import `in`.stvayush.contextualcards.utils.ImageLoader.loadImage
import `in`.stvayush.contextualcards.utils.TextFormatter.format
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
                            processDeepLink(this.url)
                        }
                    }
                }
            }
        }
    }

    inner class ImageCardViewHolder(private val imageCardBinding: LayoutImageCardBinding) {
        fun bindImageCard(card: Card) {
            with(imageCardBinding) {
                card.bgImage?.imgUrl?.let { imageCardImage.loadImage(it) }
                imageCard.setOnClickListener {
                    card.url?.let { processDeepLink(it) }
                }
            }
        }
    }

    inner class BigCardViewHolder(private val bigCardBinding: LayoutBigCardBinding) {
        fun bindBigCard(card: Card) {
            with(bigCardBinding) {
                with(card) {
                    tvTitle.format(formattedTitle, title)
                    tvDescription.format(formattedDescription, description)
                    ctaList?.let { buttonAction.configureCta(it[0]) }
                    bgImage?.imgUrl?.let { imageBigCard.loadImage(it, true) }
                    url?.let {
                        bigCardView.setOnClickListener {
                            processDeepLink(url)
                        }
                    }
                }
            }
        }
    }

    /** Since this card isn't present in response from server, so the assumption is made that it bears
     ** same attributes as big card along with 2 buttons that call for cta and (maybe) an image at center to make
     *  the card look good
     */
    inner class CenterCardViewHolder(private val centerCardBinding: LayoutCenterCardBinding) {
        fun bindCenterCard(card: Card) {
            with(centerCardBinding) {
                with(card) {
                    centerCardTitle.format(formattedTitle, title)
                    centerCardDescription.format(formattedDescription, description)
                    icon?.imgUrl?.let { centerCardIcon.loadImage(it) }
                    url?.let {
                        layoutCenterCard.setOnClickListener {
                            processDeepLink(url)
                        }
                    }
                    ctaList?.let {
                        centerCardButtonLhs.configureCta(it[0])
                        centerCardButtonRhs.configureCta(it[1])
                    }
                }
            }
        }
    }

    inner class SmallCardWithArrowViewHolder(private val layoutSmallCardWithArrowBinding: LayoutSmallCardWithArrowBinding) {
        fun bindSmallCardWithArrow(card: Card) {
            with(layoutSmallCardWithArrowBinding) {
                with(card) {
                    smallCardWithArrowTitle.format(formattedTitle, title)
                    icon?.imgUrl?.let { smallCardWithArrowImage.loadImage(it) }
                    url?.let {
                        smallCardWithArrowView.setOnClickListener {
                            processDeepLink(url)
                        }
                    }
                }
            }
        }
    }

    inner class GenzCardViewHolder(private val layoutGenzCardBinding: LayoutGenzCardBinding) {
        fun bindGenzCard(card: Card) {
            with(layoutGenzCardBinding) {
                with(card) {
                    bgImage?.imgUrl?.let { genzCardImageView.loadImage(it) }
                    url?.let { genzCardView.setOnClickListener { processDeepLink(url) } }
                }
            }
        }
    }

}
