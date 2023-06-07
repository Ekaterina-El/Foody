package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

class GetAllMealsUseCase(private val repository: MealsRepository) {
  fun getAll(): LiveData<List<Meals>> {
    return repository.getAll()
  }
}