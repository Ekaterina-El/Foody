package com.elka.foody.presentation.tags

import androidx.recyclerview.widget.RecyclerView
import com.elka.foody.databinding.TagItemBinding

class TagViewHolder(private val binding: TagItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(tag: Tag) {
    binding.title = tag.title
    binding.isActive = tag.isActive
  }
}