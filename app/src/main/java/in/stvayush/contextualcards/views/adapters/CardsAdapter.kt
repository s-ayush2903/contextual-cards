package `in`.stvayush.contextualcards.views.adapters

import `in`.stvayush.contextualcards.databinding.LayoutDynamicWidthCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardWithArrowBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutBigCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutBigCardMenuBinding
import `in`.stvayush.contextualcards.databinding.LayoutImageCardBinding
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.models.CardGroup.DesignType
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.SMALL_CARD_WITH_ARROW
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.SMALL_DISPLAY_CARD
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.IMAGE_CARD
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.DYNAMIC_WIDTH_CARD
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.BIG_DISPLAY_CARD
import `in`.stvayush.contextualcards.utils.SharedPreferenceUtils
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

    /** Sequenced on the basis of their HC values, increasing order, top to bottom */
    private lateinit var layoutSmallCardBinding: LayoutSmallCardBinding       // HC1
    private lateinit var layoutBigCardBinding: LayoutBigCardBinding           // HC3
    private lateinit var layoutImageCardBinding: LayoutImageCardBinding       // HC5
    private lateinit var layoutSmallCardWithArrowBinding: LayoutSmallCardWithArrowBinding // HC6
    private lateinit var layoutDynamicWidthCardBinding: LayoutDynamicWidthCardBinding // HC9

    private lateinit var layoutBigCardMenuBinding: LayoutBigCardMenuBinding

    /** Handling menu display for BigCard */
    private val SHOW_MENU = 1
    private val HIDE_MENU = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        lateinit var binding: ViewBinding

        binding = when (designType) {
            SMALL_DISPLAY_CARD -> {
                layoutSmallCardBinding = LayoutSmallCardBinding.inflate(inflater, parent, false)
                layoutSmallCardBinding
            }

            BIG_DISPLAY_CARD -> {
                if (viewType == SHOW_MENU) {
                    layoutBigCardMenuBinding =
                        LayoutBigCardMenuBinding.inflate(inflater, parent, false)
                    layoutBigCardMenuBinding
                } else {
                    layoutBigCardBinding = LayoutBigCardBinding.inflate(inflater, parent, false)
                    layoutBigCardBinding
                }
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

            DYNAMIC_WIDTH_CARD -> {
                layoutDynamicWidthCardBinding = LayoutDynamicWidthCardBinding.inflate(inflater, parent, false)
                layoutDynamicWidthCardBinding
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

                    BIG_DISPLAY_CARD -> {
                        if (!cardItem.swipeMenu) {
                            BigCardViewHolder(layoutBigCardBinding).bindBigCard(this@item)
                            itemView.setOnLongClickListener {
                                showMenu(position)
                                true
                            }
                        } else {
                            with(layoutBigCardMenuBinding) {
                                menuRemindIcon.setOnClickListener { deleteCard(position + 1) }
                                menuDismissIcon.setOnClickListener { deleteCard(position + 1) }
                            }
                        }
                    }

                    IMAGE_CARD -> ImageCardViewHolder(layoutImageCardBinding).bindImageCard(this@item)

                    SMALL_CARD_WITH_ARROW -> SmallCardWithArrowViewHolder(
                        layoutSmallCardWithArrowBinding
                    ).bindSmallCardWithArrow(this@item)

                    DYNAMIC_WIDTH_CARD -> DynamicWidthCardViewHolder(layoutDynamicWidthCardBinding).bindDynamicWidthCard(this@item)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (cardData[position].swipeMenu) SHOW_MENU else HIDE_MENU
    }

    fun setCardData(cardList: List<Card>) {
        cardData.clear()
        cardData.addAll(cardList)
        notifyDataSetChanged()
    }

    private fun showMenu(position: Int) {
        if (cardData.isNotEmpty() && !cardData[0].swipeMenu) {
            val menuCard = Card("menu_card")
            menuCard.swipeMenu = true
            cardData.add(position, menuCard)
            notifyDataSetChanged()
        }
    }

    private fun hideMenu() {
        if (cardData.isNotEmpty() && cardData[0].swipeMenu) {
            cardData.removeAt(0)
            notifyDataSetChanged()
        }
    }

    private fun deleteCard(position: Int) {
        if (cardData.size > position) {
            cardData.removeAt(position)
            notifyDataSetChanged()
            SharedPreferenceUtils.addGroupId(groupId.toString())
        }
        hideMenu()
    }
}
