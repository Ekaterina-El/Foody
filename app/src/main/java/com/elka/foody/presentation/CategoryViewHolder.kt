package com.elka.foody.presentation

import androidx.recyclerview.widget.RecyclerView
import com.elka.foody.databinding.CategoryItemBinding
import com.elka.foody.domain.Category

class CategoryViewHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
  fun bind(category: Category) {
    binding.category = category
  }
}