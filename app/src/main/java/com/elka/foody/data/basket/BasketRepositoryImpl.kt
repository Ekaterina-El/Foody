package com.elka.foody.data.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.domain.basket.BasketItem
import com.elka.foody.domain.basket.BasketRepository
import com.elka.foody.domain.meals.Meal
import kotlin.random.Random

object BasketRepositoryImpl : BasketRepository {
  private const val DEFINE_COUNT_OF_MEALS = 1
  private val basketItems = MutableLiveData<List<BasketItem>>(listOf())

  private val amount = MutableLiveData(0)
  private fun updateAmount() {
    amount.value = basketItems.value!!.sumOf { it.count * it.meal.price }
  }

  override fun getItems(): LiveData<List<BasketItem>> = basketItems
  override fun getAmount(): LiveData<Int> = amount

  override fun loadItems(onEnd: () -> Unit) {
    try {
      val items = mutableListOf<BasketItem>()


      //basketItems.value = items
    } catch (_: java.lang.Exception) {

    } finally {
      updateAmount()
      onEnd()
    }
  }

  override fun addMeal(meal: Meal) {
    val items = basketItems.value!!.toMutableList()

    val pos = items.indexOfFirst { it.meal.id == meal.id }
    if (pos != -1) changeCountOfMeals(meal, items[pos].count + 1)
    else {
      items.add(BasketItem(meal, count = DEFINE_COUNT_OF_MEALS))
      basketItems.value = items
      updateAmount()
    }
  }

  override fun changeCountOfMeals(meal: Meal, newCount: Int) {
    if (newCount == 0) removeMeal(meal)
    else {
      val items = basketItems.value!!.toMutableList()
      val idx = items.indexOfFirst { it.meal.id == meal.id }
      if (idx != -1) items[idx] = items[idx].copy(count = newCount)
      basketItems.value = items
    }
    updateAmount()
  }

  private fun removeMeal(meal: Meal) {
    val items = basketItems.value!!.toMutableList()
    items.removeIf { it.meal.id == meal.id }
    basketItems.value = items
    updateAmount()
  }
}