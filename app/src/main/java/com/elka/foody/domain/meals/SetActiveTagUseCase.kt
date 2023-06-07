package com.elka.foody.domain.meals

import com.elka.foody.data.meals.MealsRepositoryImpl

class SetActiveTagUseCase(private val repository: MealsRepositoryImpl) {
  fun setActive(tag: Tag) {
    repository.setActive(tag)
  }
}