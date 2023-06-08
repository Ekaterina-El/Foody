package com.elka.foody.domain.basket

import com.elka.foody.domain.meals.Meal

class AddMealUseCase(private val rep: BasketRepository) {
  fun addMeal(meal: Meal) = rep.addMeal(meal)
}