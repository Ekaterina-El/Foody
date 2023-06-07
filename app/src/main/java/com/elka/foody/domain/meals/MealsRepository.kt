package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getTags(): LiveData<List<Tag>>
  fun getMeals(): LiveData<List<Meal>>
  fun loadMeals(onEnd: () -> Unit)
  fun setActive(tag: Tag)
  fun getCurrentTag(): LiveData<Tag?>
}