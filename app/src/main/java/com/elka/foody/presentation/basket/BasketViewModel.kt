package com.elka.foody.presentation.basket

import androidx.lifecycle.ViewModel
import com.elka.foody.data.basket.BasketRepositoryImpl
import com.elka.foody.domain.basket.ChangeCountOfMealsUseCase
import com.elka.foody.domain.basket.GetAmountUseCase
import com.elka.foody.domain.basket.GetBasketItemsUseCase
import com.elka.foody.domain.basket.LoadBasketItemsUseCase
import com.elka.foody.domain.meals.Meal
import com.elka.foody.presentation.BaseViewModel
import com.elka.foody.utils.Work

class BasketViewModel: BaseViewModel() {
  private val rep = BasketRepositoryImpl
  private val changeCountOfMealsUseCase = ChangeCountOfMealsUseCase(rep)
  private val loadBasketItemsUseCase = LoadBasketItemsUseCase(rep)
  private val getBasketItemsUseCase = GetBasketItemsUseCase(rep)
  private val getAmountUseCase = GetAmountUseCase(rep)

  val basketItems = getBasketItemsUseCase.getItems()
  val amount = getAmountUseCase.getAmount()

  fun loadItems() {
    addWork(Work.LOAD_BASKET)
    loadBasketItemsUseCase.loadItems {
      removeWork(Work.LOAD_BASKET)
    }
  }

  fun changeCountOfMeals(meal: Meal, count: Int) {
    changeCountOfMealsUseCase.changeCountOfMeals(meal, count)
  }
}