package com.elka.foody.presentation.meals


import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.elka.foody.databinding.MealDialogBinding
import com.elka.foody.domain.meals.Meal

open class MealDialog(context: Context, private val listener: Listener) : Dialog(context) {
  private lateinit var binding: MealDialogBinding

  init {
    initDialog()
  }

  private fun initDialog() {
    binding = MealDialogBinding.inflate(LayoutInflater.from(context))
    binding.apply { master = this@MealDialog }
    setContentView(binding.root)
    window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
  }


  fun open(meal: Meal) {
    binding.meal = meal
    show()
  }

  fun disagree() {
    dismiss()
  }

  fun addToBasket() {
    listener.addToBasket(binding.meal!!)
  }

  companion object {
    interface Listener {
      fun addToBasket(meal: Meal)
    }
  }
}