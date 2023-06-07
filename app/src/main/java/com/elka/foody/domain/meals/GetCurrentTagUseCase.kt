package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

class GetCurrentTagUseCase(private val repository: MealsRepository) {
  fun getCurrentTag(): LiveData<Tag?> {
    return repository.getCurrentTag()
  }
}