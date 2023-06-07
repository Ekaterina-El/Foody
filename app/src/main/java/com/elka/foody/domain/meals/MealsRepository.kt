package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getTags(): LiveData<List<String>>
  fun getMeals(): LiveData<List<Meal>>
  fun loadMeals(onEnd: () -> Unit)
}