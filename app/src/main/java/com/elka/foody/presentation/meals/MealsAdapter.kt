package com.elka.foody.presentation.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elka.foody.databinding.MealItemBinding
import com.elka.foody.domain.meals.Meal

class MealsAdapter : ListAdapter<Meal, MealViewHolder>(MealItemDiffCallback()) {
  var onItemClickListener: ((Meal) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
    val binding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MealViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
    val meal = getItem(position)
    holder.bind(meal, onItemClickListener)
  }
}