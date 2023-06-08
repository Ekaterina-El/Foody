package com.elka.foody.domain.basket

class GetBasketItemsUseCase(private val rep: BasketRepository) {
  fun getItems() = rep.getItems()
}