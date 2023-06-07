package com.elka.foody.domain.meals

import com.google.gson.annotations.SerializedName

data class Meal(
  val id: Int,
  val name: String,
  val price: Int,
  val weight: Int,
  val descriptor: String,
  @SerializedName("image_url")
  val imageUrl: String,
  val tags: List<String>
)