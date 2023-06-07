package com.elka.foody.domain.meals

import androidx.lifecycle.LiveData

class GetTagsUseCase(private val repository: MealsRepository) {
  fun getTags(): LiveData<List<String>> {
    return repository.getTags()
  }
}