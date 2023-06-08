package com.elka.foody.domain.basket

import androidx.lifecycle.LiveData
import com.elka.foody.domain.meals.Meal

interface BasketRepository {
  fun getItems(): LiveData<BasketItem>
  fun addMeal(meal: Meal)
  fun changeCountOfMeals(meal: Meal, count: Int)
}