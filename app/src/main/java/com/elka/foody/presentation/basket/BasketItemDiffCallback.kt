package com.elka.foody.presentation.basket

import androidx.recyclerview.widget.DiffUtil
import com.elka.foody.domain.basket.BasketItem

class BasketItemDiffCallback: DiffUtil.ItemCallback<BasketItem>() {
  override fun areItemsTheSame(oldItem: BasketItem, newItem: BasketItem): Boolean = oldItem.meal.id == newItem.meal.id
  override fun areContentsTheSame(oldItem: BasketItem, newItem: BasketItem): Boolean = oldItem == newItem
}