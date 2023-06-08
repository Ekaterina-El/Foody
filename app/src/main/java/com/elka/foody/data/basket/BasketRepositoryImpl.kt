package com.elka.foody.data.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.basket.BasketItem
import com.elka.foody.domain.basket.BasketRepository
import com.elka.foody.domain.meals.Meal
import kotlin.random.Random

class BasketRepositoryImpl : BasketRepository {
  private val basketItems = MutableLiveData<List<BasketItem>>(listOf())

  override fun getItems(): LiveData<List<BasketItem>> = basketItems

  override fun loadItems(onEnd: () -> Unit) {
    try {
      val items = mutableListOf<BasketItem>()

      repeat(4) {
        val count = Random.nextInt(1, 10)
        val meal = Meal(
          id = it,
          name = "Meal #$it",
          price = Random.nextInt(450, 1500),
          weight = Random.nextInt(450, 670),
          description = "Description #$it",
          imageUrl = "https://www.interviewbit.com/blog/android-projects/",
          tags =  listOf()
        )
        BasketItem(meal, count)
      }

      basketItems.value = items
    } catch (_: java.lang.Exception) {

    } finally {
      onEnd()
    }
  }

  override fun addMeal(meal: Meal) {
    val items = basketItems.value!!.toMutableList()
    items.add(BasketItem(meal, count = DEFINE_COUNT_OF_MEALS))
    basketItems.value = items
  }

  override fun changeCountOfMeals(meal: Meal, newCount: Int) {
    if (newCount == 0) removeMeal(meal)
    else {
      val items = basketItems.value!!.toMutableList()
      val idx = items.indexOfFirst { it.meal.id == meal.id }
      if (idx != -1) items[idx] = items[idx].copy(count = newCount)
      basketItems.value = items
    }
  }

  private fun removeMeal(meal: Meal) {
    val items = basketItems.value!!.toMutableList()
    items.removeIf { it.meal.id == meal.id }
    basketItems.value = items
  }

  companion object {
    private const val DEFINE_COUNT_OF_MEALS = 1
  }
}