package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

data class CardGroupObject (
    @SerializedName("card_groups")
    val cardGroup: List<CardGroup>
)
