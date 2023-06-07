package com.elka.foody.data.meals

import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.meals.Meal
import com.elka.foody.domain.meals.MealsRepository
import com.elka.foody.domain.meals.Tag

object MealsRepositoryImpl : MealsRepository {
  private val api = MealsAPI

  private val sortedMeals = MutableLiveData<List<Meal>>(listOf())
  private val meals = MutableLiveData<List<Meal>>(listOf())

  private val tags = MutableLiveData<List<Tag>>(listOf())
  private val currentTag = MutableLiveData<Tag?>(null)

  override fun getMeals(): LiveData<List<Meal>> {
    return sortedMeals
  }

  override fun getTags(): LiveData<List<Tag>> {
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
      sortMeals()
      onEnd()
    }
  }

  private fun sortMeals() {
    val currentTag = currentTag.value!!.title
    sortedMeals.value = meals.value!!.filter {it.tags.contains(currentTag) }
  }

  override fun setActive(tag: Tag) {
    val currentTagTitle = currentTag.value?.title ?: ""
    if (currentTagTitle == tag.title) return

    tags.value = tags.value!!.map {
      return@map when (it.title) {
        currentTagTitle -> it.copy(isActive = false)
        tag.title -> it.copy(isActive = true)
        else -> it
      }
    }
    currentTag.value = tag
    sortMeals()
  }

  override fun getCurrentTag(): LiveData<Tag?> {
    return currentTag
  }

  private fun updateTags(meals: List<Meal>) {
    val tagsTitle = mutableSetOf<String>()
    meals.forEach { meal ->
      meal.tags.forEach { tag ->
        tagsTitle.add(tag)
      }
    }
    val tags = tagsTitle.map { Tag(title = it, isActive = false) }
    this@MealsRepositoryImpl.tags.value = tags
    currentTag.value = null
    setActive(tags[0])
  }
}