package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

data class Gradient(
    @SerializedName("colors")
    val colorList: List<String>,

    val angle: Double

)
