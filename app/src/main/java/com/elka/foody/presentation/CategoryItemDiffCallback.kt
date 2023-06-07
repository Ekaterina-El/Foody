package com.elka.foody.presentation

import androidx.recyclerview.widget.DiffUtil
import com.elka.foody.domain.Category

class CategoryItemDiffCallback: DiffUtil.ItemCallback<Category>() {
  override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.id == newItem.id
  override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
}