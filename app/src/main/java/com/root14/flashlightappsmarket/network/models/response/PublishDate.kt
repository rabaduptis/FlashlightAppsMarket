package com.root14.flashlightappsmarket.network.models.response

import com.google.gson.annotations.SerializedName


data class PublishDate (

  @SerializedName("year"  ) var year  : Int? = null,
  @SerializedName("month" ) var month : Int? = null,
  @SerializedName("day"   ) var day   : Int? = null

)