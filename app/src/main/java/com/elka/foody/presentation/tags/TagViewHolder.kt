package com.elka.foody.presentation.tags

import androidx.recyclerview.widget.RecyclerView
import com.elka.foody.databinding.TagItemBinding
import com.elka.foody.domain.meals.Tag

class TagViewHolder(private val binding: TagItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(tag: Tag, onClickListener: (Tag) -> Unit) {
    binding.title = tag.title
    binding.isActive = tag.isActive

    binding.root.setOnClickListener { onClickListener(tag) }
  }
}