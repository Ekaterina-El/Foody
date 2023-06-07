package com.elka.foody.presentation.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elka.foody.data.CategoryRepositoryImpl
import com.elka.foody.domain.GetCategoriesUseCase
import com.elka.foody.domain.LoadCategoriesUseCase
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