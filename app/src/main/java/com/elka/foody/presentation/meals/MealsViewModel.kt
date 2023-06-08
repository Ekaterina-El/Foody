package com.elka.foody.presentation.meals

import com.elka.foody.data.basket.BasketRepositoryImpl
import com.elka.foody.data.meals.MealsRepositoryImpl
import com.elka.foody.domain.basket.AddMealUseCase
import com.elka.foody.domain.meals.*
import com.elka.foody.presentation.BaseViewModel
import com.elka.foody.utils.Work

class MealsViewModel: BaseViewModel() {
  private val repository = MealsRepositoryImpl
  private val loadMealsUseCase = LoadMealsUseCase(repository)
  private val getAllMealsUseCase = GetAllMealsUseCase(repository)

  private val setActiveTagUseCase = SetActiveTagUseCase(repository)
  private val getTagsUseCase = GetTagsUseCase(repository)
  private val getCurrentTagUseCase = GetCurrentTagUseCase(repository)

  private val basketRep = BasketRepositoryImpl
  private val addMealUseCase = AddMealUseCase(basketRep)

  val meals = getAllMealsUseCase.getAll()
  val tags = getTagsUseCase.getTags()

  fun loadMeals() {
    addWork(Work.LOAD_MEALS)
    loadMealsUseCase.loadMeals {
      removeWork(Work.LOAD_MEALS)
    }
  }

  fun setActiveTag(it: Tag) {
    setActiveTagUseCase.setActive(it)
  }

  fun addToBasket(meal: Meal) {
    addMealUseCase.addMeal(meal)
  }
}