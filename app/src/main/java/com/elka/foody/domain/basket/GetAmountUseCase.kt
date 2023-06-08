package com.elka.foody.domain.basket

class GetAmountUseCase(private val rep: BasketRepository) {
  fun getAmount() = rep.getAmount()
}