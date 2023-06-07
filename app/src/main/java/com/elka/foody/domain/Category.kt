package com.elka.foody.domain

data class Category(
  val id: Int = UNDEFINED_ID,
  val name: String = "",
  val imageUrl: String = ""
) {
  companion object {
    const val UNDEFINED_ID = -1
  }
}
