package com.elka.foody.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elka.foody.databinding.CategoryItemBinding
import com.elka.foody.domain.categories.Category

class CategoriesAdapter: ListAdapter<Category, CategoryViewHolder>(CategoryItemDiffCallback()) {
  var onItemClickListener: ((Category) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
    val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CategoryViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
    val category = getItem(position)
    holder.bind(category, onItemClickListener)
  }
}