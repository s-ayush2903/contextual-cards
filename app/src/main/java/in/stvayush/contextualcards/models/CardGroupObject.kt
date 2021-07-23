package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

/**
 * This data class is used to parse out the `card_groups` object returned from
 * the Web API response which contains a List of [CardGroup]
 */
data class CardGroupObject (
    @SerializedName("card_groups")
    val cardGroup: List<CardGroup>
)
