package com.root14.flashlightappsmarket.network.models.response

import com.google.gson.annotations.SerializedName


data class AppResponse (

  @SerializedName("packageName"      ) var packageName      : String?      = null,
  @SerializedName("name"             ) var name             : String?      = null,
  @SerializedName("iconUrl"          ) var iconUrl          : String?      = null,
  @SerializedName("price"            ) var price            : String?      = null,
  @SerializedName("ratingValue"      ) var ratingValue      : Double?      = null,
  @SerializedName("ratingCount"      ) var ratingCount      : Int?         = null,
  @SerializedName("downloads"        ) var downloads        : String?      = null,
  @SerializedName("publishDate"      ) var publishDate      : PublishDate? = PublishDate(),
  @SerializedName("version"          ) var version          : String?      = null,
  @SerializedName("category"         ) var category         : String?      = null,
  @SerializedName("developerName"    ) var developerName    : String?      = null,
  @SerializedName("developerEmail"   ) var developerEmail   : String?      = null,
  @SerializedName("developerAddress" ) var developerAddress : String?      = null

)