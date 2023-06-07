package com.elka.foody.presentation.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elka.foody.databinding.TagItemBinding
import com.elka.foody.domain.meals.Tag

class TagAdapter(private val onClickListener: (Tag) -> Unit): ListAdapter<Tag, TagViewHolder>(TagItemDiffCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
    val binding = TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return TagViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
    holder.bind(getItem(position), onClickListener)
  }
}