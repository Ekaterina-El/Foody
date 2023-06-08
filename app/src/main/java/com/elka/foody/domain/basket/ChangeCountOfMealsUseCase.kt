package com.elka.foody.domain.basket

import com.elka.foody.domain.meals.Meal

class ChangeCountOfMealsUseCase(private val rep: BasketRepository) {
  fun changeCountOfMeals(meal: Meal, count: Int) = rep.changeCountOfMeals(meal, count)
}