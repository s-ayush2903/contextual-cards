package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

data class FormattedText(
    val text: String,

    @SerializedName("entities")
    val entityList: List<Entity>
)
