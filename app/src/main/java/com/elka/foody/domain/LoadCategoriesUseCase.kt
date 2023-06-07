package com.elka.foody.domain

class LoadCategoriesUseCase(private val repository: CategoryRepository) {
  fun loadCategories() {
    repository.loadCategories()
  }
}