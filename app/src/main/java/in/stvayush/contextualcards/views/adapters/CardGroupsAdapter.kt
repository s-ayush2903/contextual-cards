package `in`.stvayush.contextualcards.views.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import `in`.stvayush.contextualcards.databinding.LayoutCardGroupBinding
import `in`.stvayush.contextualcards.models.CardGroup
import `in`.stvayush.contextualcards.views.viewholders.CardGroupViewHolder

/** Authored by Ayush Shrivastava on 22/7/21 */

/**
 * An adapter that binds the [CardGroup]s data to render on the screen A point to note is that for
 * each [CardGroup], a separate and independent [RecyclerView] is being created
 */
class CardGroupsAdapter(private val context: Context) :
  RecyclerView.Adapter<CardGroupViewHolder>() {
  private val TAG = "CardGroupsAdapter"
  private val cardGroupData = ArrayList<CardGroup>()

  private lateinit var cardGroupBinding: LayoutCardGroupBinding

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGroupViewHolder {
    cardGroupBinding =
      LayoutCardGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CardGroupViewHolder(cardGroupBinding)
  }

  override fun onBindViewHolder(holder: CardGroupViewHolder, position: Int) {
    val currentCardGroup: CardGroup = cardGroupData[position]
    val cardsAdapter = CardsAdapter(currentCardGroup.designType, currentCardGroup.id)
    holder.cardGroupRecyclerView.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      adapter = cardsAdapter
      addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
              Log.d(TAG, "onScrollStateChanged: hiding menu")
              cardsAdapter.hideMenu()
            }
          }
        }
      )
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
