package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

data class Card(
    val name: String,

    @SerializedName("formatted_title")
    val formattedTitle: FormattedText? = null,

    val title: String? = null,

    @SerializedName("formatted_description")
    val formattedDescription: FormattedText? = null,

    val description: String? = null,

    @SerializedName("icon")
    val icon: CardImage? = null,

    val url: String? = null,

    @SerializedName("bg_image")
    val bgImage: CardImage? = null,

    @SerializedName("bg_color")
    val bgColor: String? = null,

    @SerializedName("bg_gradient")
    val bgGradient: Gradient? = null,

    @SerializedName("cta")
    val ctaList: List<Cta>? = null,

    var swipeMenu: Boolean = false
)
