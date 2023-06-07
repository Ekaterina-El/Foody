package com.elka.foody.presentation.categories

import androidx.recyclerview.widget.DiffUtil
import com.elka.foody.domain.categories.Category

class CategoryItemDiffCallback: DiffUtil.ItemCallback<Category>() {
  override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.id == newItem.id
  override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
}