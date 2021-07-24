package `in`.stvayush.contextualcards.views.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import `in`.stvayush.contextualcards.databinding.LayoutBigCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutDynamicWidthCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutImageCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardWithArrowBinding
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.utils.CtaConfigurer.configureCta
import `in`.stvayush.contextualcards.utils.DeepLinkParser.processDeepLink
import `in`.stvayush.contextualcards.utils.ImageLoader.loadImage
import `in`.stvayush.contextualcards.utils.TextFormatter.format

/** Authored by Ayush Shrivastava on 21/7/21 */

/**
 * A parent ViewHolder that contains the API for binding of Views/Layouts([Card]s) It contains many
 * ViewHolders for each specific [DesignType] of Card, each of which takes a viewBinding as a
 * parameter to ease referring to the layouts and also produces cleaner & readable API
 */
class CardViewHolder(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {

  /**
   * An ideology followed here while configuring listeners on the cards having urls, is that
   * Listener is configured on the Card ONLY if the card contains a @param [Card.url], because in
   * opposite case, it does not seem reasonable to have a listener on a View which is just of no
   * use(un-interactive), as it can't trigger or start a new action or change, and also clicking on
   * that particular view will (although not significantly, but still some)increase in memory load
   * chunk of application from the entire pie available to all apps and will consume resources,
   * which do not seem to reasonable in this case.
   */
  inner class SmallCardViewHolder(private val smallCardBinding: LayoutSmallCardBinding) {
    fun bindSmallCard(card: Card) {
      with(smallCardBinding) {
        with(card) {
          smallCardTitle.format(formattedTitle, title)
          smallCardDescription.format(formattedDescription, description)
          icon?.imgUrl?.let { smallCardIcon.loadImage(it) }
          url?.let { smallCardView.setOnClickListener { processDeepLink(url) } }
        }
      }
    }
  }

  inner class ImageCardViewHolder(private val imageCardBinding: LayoutImageCardBinding) {
    fun bindImageCard(card: Card) {
      with(imageCardBinding) {
        with(card) {
          bgImage?.imgUrl?.let { imageCardImage.loadImage(it) }
          url?.let { imageCard.setOnClickListener { processDeepLink(url) } }
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
          url?.let { bigCardView.setOnClickListener { processDeepLink(url) } }
        }
      }
    }
  }

  inner class SmallCardWithArrowViewHolder(
    private val layoutSmallCardWithArrowBinding: LayoutSmallCardWithArrowBinding
  ) {
    fun bindSmallCardWithArrow(card: Card) {
      with(layoutSmallCardWithArrowBinding) {
        with(card) {
          smallCardWithArrowTitle.format(formattedTitle, title)
          icon?.imgUrl?.let { smallCardWithArrowImage.loadImage(it) }
          url?.let { smallCardWithArrowView.setOnClickListener { processDeepLink(url) } }
        }
      }
    }
  }

  inner class DynamicWidthCardViewHolder(
    private val layoutDynamicWidthCardBinding: LayoutDynamicWidthCardBinding
  ) {
    fun bindDynamicWidthCard(card: Card) {
      with(layoutDynamicWidthCardBinding) {
        with(card) {
          bgImage?.imgUrl?.let { dynamicWidthCardImageView.loadImage(it) }
          url?.let { dynamicWidthCardView.setOnClickListener { processDeepLink(url) } }
        }
      }
    }
  }
}
