package com.elka.foody.presentation.meals

import androidx.recyclerview.widget.DiffUtil
import com.elka.foody.domain.meals.Meal

class MealItemDiffCallback : DiffUtil.ItemCallback<Meal>() {
  override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.name == newItem.name
  override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
}