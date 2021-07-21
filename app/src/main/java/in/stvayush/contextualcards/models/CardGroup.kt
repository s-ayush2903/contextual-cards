package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

data class CardGroup(
    val id: Long,
    val name: String,

    @SerializedName("design_type")
    val designType: DesignType,

    @SerializedName("cards")
    val cardList: List<Card>,

    @SerializedName("is_scrollable")
    val scrollable: Boolean
) {
    enum class DesignType {

        @SerializedName("HC1")
        SMALL_DISPLAY_CARD,

        @SerializedName("HC3")
        BIG_DISPLAY_CARD,

        @SerializedName("HC4")
        CENTER_CARD,

        @SerializedName("HC5")
        IMAGE_CARD,

        @SerializedName("HC6")
        SMALL_CARD_WITH_ARROW,

        @SerializedName("HC9")
        GENZ_CARD

    }
}

