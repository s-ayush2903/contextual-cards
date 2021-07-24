package `in`.stvayush.contextualcards.models

import com.google.gson.annotations.SerializedName

data class CardImage(
  @SerializedName("image_type") val imgType: String,
  @SerializedName("image_url") val imgUrl: String? = null,
  @SerializedName("asset_type") val assetType: String? = null
)
