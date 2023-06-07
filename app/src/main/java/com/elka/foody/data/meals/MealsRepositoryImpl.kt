package com.elka.foody.data.meals

import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.meals.Meal
import com.elka.foody.domain.meals.MealsRepository

object MealsRepositoryImpl : MealsRepository {
  private val api = MealsAPI
  private val meals = MutableLiveData<List<Meal>>(listOf())

  override fun getAll(): LiveData<List<Meal>> {
    return meals
  }

  override fun loadMeals(onEnd: () -> Unit) {
    api.getAll({ onEnd() }) { meals ->
      val m = meals.dishes.map {
        val description = it.description
        return@map if (URLUtil.isValidUrl(description)) it.copy(
          imageUrl = description,
          description = ""
        )
        else it
      }
      this@MealsRepositoryImpl.meals.value = m
      onEnd()
    }
  }
}