package com.elka.foody.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.elka.foody.R

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
  Glide.with(imageView.context)
    .load(url)
    .centerCrop()
    .placeholder(R.drawable.placeholder)
    .into(imageView)
}

@BindingAdapter("app:imageUrlWithoutCrop")
fun loadImageWithoutCrop(imageView: ImageView, url: String?) {
  imageView.setBackgroundColor(imageView.context.getColor(R.color.r1))
  Glide.with(imageView.context)
    .load(url)
    .placeholder(R.drawable.placeholder)
    .into(imageView)
}