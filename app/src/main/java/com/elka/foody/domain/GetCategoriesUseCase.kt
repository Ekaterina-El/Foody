package com.elka.foody.domain

import androidx.lifecycle.LiveData

class GetCategoriesUseCase(private val repository: CategoryRepository) {
  fun getCategories(): LiveData<List<Category>> {
    return repository.getCategories()
  }
}