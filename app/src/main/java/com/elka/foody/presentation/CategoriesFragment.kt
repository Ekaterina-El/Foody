package com.elka.foody.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elka.foody.databinding.CategoriesFragmentBinding

class CategoriesFragment: Fragment() {
  private lateinit var binding: CategoriesFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = CategoriesFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }
}