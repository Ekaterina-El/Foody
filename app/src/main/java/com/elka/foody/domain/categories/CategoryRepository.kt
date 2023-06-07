package com.elka.foody.domain.categories

import androidx.lifecycle.LiveData

interface CategoryRepository {
  fun getCategories(): LiveData<List<Category>>
  fun loadCategories(onEnd: () -> Unit)
}