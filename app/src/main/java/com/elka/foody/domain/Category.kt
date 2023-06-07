package com.elka.foody.domain

import com.google.gson.annotations.SerializedName

data class Category(
  val id: Int = UNDEFINED_ID,
  val name: String = "",

  @SerializedName("image_url")
  val imageUrl: String = ""
) {
  companion object {
    const val UNDEFINED_ID = -1
  }
}
