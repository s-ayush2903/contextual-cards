package `in`.stvayush.contextualcards.views.viewholders

import `in`.stvayush.contextualcards.databinding.LayoutCardGroupBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */

/** A ViewHolder that exposes the recyclerView associated to it and helps completing the Adapter Pattern
 * (for displaying views in recyclerViews using adapters & ViewHolders)
 * */
class CardGroupViewHolder(cardGroupBinding: LayoutCardGroupBinding): RecyclerView.ViewHolder(cardGroupBinding.root) {
    val cardGroupRecyclerView = cardGroupBinding.rvCardGroup
}
