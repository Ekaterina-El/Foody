package com.elka.foody.presentation.categories

import com.elka.foody.data.CategoryRepositoryImpl
import com.elka.foody.domain.categories.GetCategoriesUseCase
import com.elka.foody.domain.categories.LoadCategoriesUseCase
import com.elka.foody.presentation.BaseViewModel
import com.elka.foody.utils.Work

class CategoriesViewModel: BaseViewModel() {

  private val repository = CategoryRepositoryImpl
  private val loadCategoriesUseCase = LoadCategoriesUseCase(repository)
  private val getCategoriesUseCase = GetCategoriesUseCase(repository)

  val categories = getCategoriesUseCase.getCategories()
  fun loadCategories() {
    addWork(Work.LOAD_CATEGORIES)
    loadCategoriesUseCase.loadCategories {
      removeWork(Work.LOAD_CATEGORIES)
    }
  }
}