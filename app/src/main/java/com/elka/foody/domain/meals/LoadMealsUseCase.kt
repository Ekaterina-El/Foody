package com.elka.foody.domain.meals

class LoadMealsUseCase(private val repository: MealsRepository) {
  fun loadMeals(onEnd: () -> Unit) {
    return repository.loadMeals(onEnd)
  }
}