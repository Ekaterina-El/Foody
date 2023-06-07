package com.elka.foody.data.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.categories.Category
import com.elka.foody.domain.categories.CategoryRepository

object CategoryRepositoryImpl: CategoryRepository {
  private val api = CategoriesAPI
  private val categories = MutableLiveData<List<Category>>(listOf())

  override fun getCategories(): LiveData<List<Category>> {
    return categories
  }

  override fun loadCategories(onEnd: () -> Unit) {
    CategoriesAPI.getAll({ onEnd() }) {
      categories.value = it.сategories
      onEnd()
    }
  }
}