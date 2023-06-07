package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getAll(): LiveData<List<Meals>>
  fun loadMeals(onEnd: () -> Unit)
}