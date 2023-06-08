package com.elka.foody.domain.basket

import com.elka.foody.domain.meals.Meal

class LoadBasketItemsUseCase(private val rep: BasketRepository) {
  fun loadItems(onEnd: () -> Unit) = rep.loadItems(onEnd)
}