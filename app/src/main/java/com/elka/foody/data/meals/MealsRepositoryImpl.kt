package com.elka.foody.data.meals

import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.meals.Meal
import com.elka.foody.domain.meals.MealsRepository

object MealsRepositoryImpl : MealsRepository {
  private val api = MealsAPI
  private val meals = MutableLiveData<List<Meal>>(listOf())
  private val tags = MutableLiveData<List<String>>(listOf())

  override fun getMeals(): LiveData<List<Meal>> {
    return meals
  }

  override fun getTags(): LiveData<List<String>> {
    return tags
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
      updateTags(m)
      onEnd()
    }
  }

  private fun updateTags(meals: List<Meal>) {
    val tags = mutableSetOf<String>()
    meals.forEach { meal ->
      meal.tags.forEach { tag ->
        tags.add(tag)
      }
    }
    this@MealsRepositoryImpl.tags.value = tags.toList()
  }
}