package com.elka.foody.data.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.meals.Meals
import com.elka.foody.domain.meals.MealsRepository

object MealsRepositoryImpl : MealsRepository {
  private val api = MealsAPI
  private val meals = MutableLiveData<List<Meals>>(listOf())

  override fun getAll(): LiveData<List<Meals>> {
    return meals
  }

  override fun loadMeals(onEnd: () -> Unit) {
    MealsAPI.getAll({ onEnd() }) { onEnd() }
  }
}