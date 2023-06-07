package com.elka.foody.domain

import androidx.lifecycle.LiveData

interface CategoryRepository {
  fun getCategories(): LiveData<List<Category>>
  fun loadCategories()
}