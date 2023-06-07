package com.elka.foody.presentation.meals

import androidx.recyclerview.widget.RecyclerView
import com.elka.foody.databinding.MealItemBinding
import com.elka.foody.domain.meals.Meal

class MealViewHolder(private val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(meal: Meal, onItemClickListener: ((Meal) -> Unit)?) {
    binding.meal = meal
    binding.root.setOnClickListener { onItemClickListener?.invoke(meal) }
    binding.image.setOnClickListener { onItemClickListener?.invoke(meal) }
  }
}