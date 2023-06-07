package com.elka.foody.presentation.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elka.foody.databinding.TagItemBinding

class TagAdapter: ListAdapter<Tag, TagViewHolder>(TagItemDiffCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
    val binding = TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return TagViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}