package `in`.stvayush.contextualcards.views.adapters

import `in`.stvayush.contextualcards.databinding.*
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.models.CardGroup.DesignType
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.*
import `in`.stvayush.contextualcards.views.viewholders.CardViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

class CardsAdapter(private val designType: DesignType, private val groupId: Long) :
    RecyclerView.Adapter<CardViewHolder>() {
    private val TAG = "CardsAdapter"
    private val cardData = ArrayList<Card>()

    // Sequenced on the basis of their HC values, increasing order, top to bottom
    private lateinit var layoutSmallCardBinding: LayoutSmallCardBinding     // HC1
    private lateinit var layoutBigCardBinding: LayoutBigCardBinding         // HC3
    private lateinit var layoutCenterCardBinding: LayoutCenterCardBinding   // HC4
    private lateinit var layoutImageCardBinding: LayoutImageCardBinding     // HC5
    private lateinit var layoutSmallCardWithArrowBinding: LayoutSmallCardWithArrowBinding // HC6
    private lateinit var layoutGenzCardBinding: LayoutGenzCardBinding       // HC9

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        lateinit var binding: ViewBinding

        binding = when (designType) {
            SMALL_DISPLAY_CARD -> {
                layoutSmallCardBinding = LayoutSmallCardBinding.inflate(inflater, parent, false)
                layoutSmallCardBinding
            }

            BIG_DISPLAY_CARD -> {
                layoutBigCardBinding = LayoutBigCardBinding.inflate(inflater, parent, false)
                layoutBigCardBinding
            }

            CENTER_CARD -> {
                layoutCenterCardBinding = LayoutCenterCardBinding.inflate(inflater, parent, false)
                layoutCenterCardBinding
            }
            IMAGE_CARD -> {
                layoutImageCardBinding = LayoutImageCardBinding.inflate(inflater, parent, false)
                layoutImageCardBinding
            }
            SMALL_CARD_WITH_ARROW -> {
                layoutSmallCardWithArrowBinding =
                    LayoutSmallCardWithArrowBinding.inflate(inflater, parent, false)
                layoutSmallCardWithArrowBinding
            }

            GENZ_CARD -> {
                layoutGenzCardBinding = LayoutGenzCardBinding.inflate(inflater, parent, false)
                layoutGenzCardBinding
            }
        }

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardItem = cardData[position]
        with(cardItem) item@{
            with(holder) {
                when (designType) {
                    SMALL_DISPLAY_CARD -> SmallCardViewHolder(layoutSmallCardBinding).bindSmallCard(
                        this@item
                    )

                    BIG_DISPLAY_CARD -> BigCardViewHolder(layoutBigCardBinding).bindBigCard(this@item)

                    CENTER_CARD -> CenterCardViewHolder(layoutCenterCardBinding).bindCenterCard(this@item)

                    IMAGE_CARD -> ImageCardViewHolder(layoutImageCardBinding).bindImageCard(this@item)

                    SMALL_CARD_WITH_ARROW -> SmallCardWithArrowViewHolder(
                        layoutSmallCardWithArrowBinding
                    ).bindSmallCardWithArrow(this@item)

                    GENZ_CARD -> GenzCardViewHolder(layoutGenzCardBinding).bindGenzCard(this@item)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    fun setCardData(cardList: List<Card>) {
        cardData.clear()
        cardData.addAll(cardList)
        notifyDataSetChanged()
    }

}
