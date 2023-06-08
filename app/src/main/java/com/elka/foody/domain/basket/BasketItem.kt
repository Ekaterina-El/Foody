package com.elka.foody.domain.basket

import com.elka.foody.domain.meals.Meal

data class BasketItem (
  val meal: Meal,
  val count: Int
)
