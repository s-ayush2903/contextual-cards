package `in`.stvayush.contextualcards.views.adapters

import `in`.stvayush.contextualcards.databinding.LayoutCardGroupBinding
import `in`.stvayush.contextualcards.models.Card
import `in`.stvayush.contextualcards.models.CardGroup
import `in`.stvayush.contextualcards.views.viewholders.CardGroupViewHolder
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */


// see if we need to go back to RV.VH
class CardGroupsAdapter(private val context: Context) : RecyclerView.Adapter<CardGroupViewHolder>() {
    private val TAG = "CardGroupsAdapter"
    private val cardGroupData = ArrayList<CardGroup>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGroupViewHolder {
        return CardGroupViewHolder(
            LayoutCardGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardGroupViewHolder, position: Int) {
        val currentCardGroup: CardGroup = cardGroupData[position]
        val cardsAdapter = CardsAdapter(currentCardGroup.designType, currentCardGroup.id)
        holder.cardGroupRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = cardsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                        Log.d(TAG, "onScrollStateChanged: implement it via hiding menu")
                    }
                }
            })
        }
        cardsAdapter.setCardData(cardGroupData[position].cardList)
    }

    override fun getItemCount(): Int {
        return cardGroupData.size
    }

    fun setCardGroupData(cardGroupList: List<CardGroup>) {
        cardGroupData.clear()
        cardGroupData.addAll(cardGroupList)
        notifyDataSetChanged()

    }
}