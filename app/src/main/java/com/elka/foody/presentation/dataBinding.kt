package com.elka.foody.presentation

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.elka.foody.R
import com.elka.foody.utils.splitBy3

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
    .dontAnimate()
    .into(imageView)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:basketBuy")
fun showBasketBuyText(button: Button, amount: Int) {
  if (amount == 0) button.isEnabled = false
  button.text = button.context.getString(R.string.buy) + " " + amount.splitBy3(amount) + " ₽"
}