package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getAll(): LiveData<List<Meal>>
  fun loadMeals(onEnd: () -> Unit)
}