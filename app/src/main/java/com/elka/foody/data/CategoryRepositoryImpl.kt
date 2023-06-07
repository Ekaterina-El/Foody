package com.elka.foody.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.Category
import com.elka.foody.domain.CategoryRepository

object CategoryRepositoryImpl: CategoryRepository {
  private val api = CategoriesAPI
  private val categories = MutableLiveData<List<Category>>(listOf())

  override fun getCategories(): LiveData<List<Category>> {
    return categories
  }

  override fun loadCategories() {
    api.getAll {
      categories.value = it.сategories
    }
  }
}