package com.elka.foody.domain.categories

class LoadCategoriesUseCase(private val repository: CategoryRepository) {
  fun loadCategories(onEnd: () -> Unit) {
    repository.loadCategories(onEnd)
  }
}