package com.elka.foody.presentation.tags

import androidx.recyclerview.widget.DiffUtil
import com.elka.foody.domain.meals.Tag

class TagItemDiffCallback: DiffUtil.ItemCallback<Tag>() {
  override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean  = oldItem.title == newItem.title
  override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean = oldItem == newItem
}