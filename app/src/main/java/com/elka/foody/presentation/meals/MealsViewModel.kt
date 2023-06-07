package com.elka.foody.presentation.meals

import com.elka.foody.data.categories.CategoryRepositoryImpl
import com.elka.foody.data.meals.MealsRepositoryImpl
import com.elka.foody.domain.categories.GetCategoriesUseCase
import com.elka.foody.domain.categories.LoadCategoriesUseCase
import com.elka.foody.domain.meals.GetAllMealsUseCase
import com.elka.foody.domain.meals.LoadMealsUseCase
import com.elka.foody.presentation.BaseViewModel
import com.elka.foody.utils.Work

class MealsViewModel: BaseViewModel() {
  private val repository = MealsRepositoryImpl
  private val loadMealsUseCase = LoadMealsUseCase(repository)
  private val getAllMealsUseCase = GetAllMealsUseCase(repository)

  val meals = getAllMealsUseCase.getAll()

  fun loadMeals() {
    addWork(Work.LOAD_MEALS)
    loadMealsUseCase.loadMeals {
      removeWork(Work.LOAD_MEALS)
    }
  }
}