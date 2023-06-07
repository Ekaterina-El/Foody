package com.elka.foody.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elka.foody.R
import com.elka.foody.data.CategoryRepositoryImpl
import com.elka.foody.domain.CategoryRepository

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    CategoryRepositoryImpl.loadCategories()
  }
}