package com.elka.foody.presentation.basket

import androidx.recyclerview.widget.RecyclerView
import com.elka.foody.databinding.BasketItemBinding
import com.elka.foody.databinding.MealItemBinding
import com.elka.foody.domain.basket.BasketItem
import com.elka.foody.domain.meals.Meal

class BasketViewHolder(private val binding: BasketItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(basketItem: BasketItem, onItemClickListener: ((Meal, Int) -> Unit)?) {
    binding.basketItem = basketItem
    binding.minus.setOnClickListener { onItemClickListener?.invoke(basketItem.meal, basketItem.count - 1) }
    binding.plus.setOnClickListener { onItemClickListener?.invoke(basketItem.meal, basketItem.count + 1) }
  }
}