package `in`.stvayush.contextualcards.views.adapters

import `in`.stvayush.contextualcards.databinding.LayoutImageCardBinding
import `in`.stvayush.contextualcards.databinding.LayoutSmallCardBinding
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.models.CardGroup.DesignType
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.IMAGE_CARD
import `in`.stvayush.contextualcards.models.CardGroup.DesignType.SMALL_DISPLAY_CARD
import `in`.stvayush.contextualcards.views.viewholders.CardViewHolder
import android.util.Log
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
    private lateinit var layoutImageCardBinding: LayoutImageCardBinding
    private lateinit var layoutSmallCardBinding: LayoutSmallCardBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        var binding: ViewBinding = LayoutImageCardBinding.inflate(inflater, parent, false)

        when (designType) {
            IMAGE_CARD -> {
                binding = LayoutImageCardBinding.inflate(inflater, parent, false)
                Log.d(TAG, "onCreateViewHolder: inflating image card.....")
                layoutImageCardBinding = binding
            }
            SMALL_DISPLAY_CARD -> {
                binding = LayoutSmallCardBinding.inflate(inflater, parent, false)
                layoutSmallCardBinding = binding
            }
            else -> Log.d(TAG, "onCreateViewHolder: To be implemented!")
        }

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardItem = cardData[position]
        with(cardItem) item@{
            with(holder) {
                when (designType) {
                    IMAGE_CARD -> ImageCardViewHolder(layoutImageCardBinding).bindImageCard(this@item)
                    SMALL_DISPLAY_CARD -> SmallCardViewHolder(layoutSmallCardBinding).bindSmallCard(
                        this@item
                    )
                    else -> {
                        Log.d(TAG, "onBindViewHolder: To be implemented!!")
                    }
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
