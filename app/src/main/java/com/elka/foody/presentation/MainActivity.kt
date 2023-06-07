package com.elka.foody.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.elka.foody.R
import com.elka.foody.data.CategoryRepositoryImpl
import com.elka.foody.databinding.ActivityMainBinding
import com.elka.foody.domain.CategoryRepository

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupNavigation()
    CategoryRepositoryImpl.loadCategories()
  }

  private fun setupNavigation() {
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.appNavContainer) as NavHostFragment
    val navController = navHostFragment.navController
    binding.menu.setupWithNavController(navController)
  }
}