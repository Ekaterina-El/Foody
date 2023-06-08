package com.elka.foody.presentation.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elka.foody.databinding.BasketItemBinding
import com.elka.foody.databinding.MealItemBinding
import com.elka.foody.domain.basket.BasketItem
import com.elka.foody.domain.meals.Meal
import com.elka.foody.presentation.meals.MealItemDiffCallback
import com.elka.foody.presentation.meals.MealViewHolder

class BasketAdapter : ListAdapter<BasketItem, BasketViewHolder>(BasketItemDiffCallback()) {
  var onItemClickListener: ((Meal, Int) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
    val binding = BasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return BasketViewHolder(binding)
  }

  override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
    val basketItem = getItem(position)
    holder.bind(basketItem, onItemClickListener)
  }
}