package com.elka.foody.presentation.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.elka.foody.R
import com.elka.foody.databinding.MealsFragmentBinding
import com.elka.foody.domain.meals.Meal
import com.elka.foody.presentation.GridItemDecorator
import com.elka.foody.utils.Work
import com.elka.foody.utils.hasLoads

class MealsFragment : Fragment() {
  private lateinit var binding: MealsFragmentBinding
  private val adapter by lazy { MealsAdapter() }
  private val mealsViewModel by lazy { ViewModelProvider(this)[MealsViewModel::class.java] }

  private val works = listOf(
    Work.LOAD_MEALS,
  )

  private val hasLoads: Boolean
    get() {
      val w = mealsViewModel.work.value!!.toMutableList()
      return hasLoads(works, w)
    }


  private val workObserver = Observer<List<Work>> {
    binding.swiper.isRefreshing = hasLoads
  }

  private val mealsObserver = Observer<List<Meal>> {
    adapter.submitList(it)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = MealsFragmentBinding.inflate(inflater, container, false)
    binding.apply {
      lifecycleOwner = viewLifecycleOwner
      adapter = this@MealsFragment.adapter
    }
    setupMealsList()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.category = "Азиатская кухня"

    update()
    with(binding.swiper) {
      val color = requireContext().getColor(R.color.swiper_color)
      setColorSchemeColors(color)
      setOnRefreshListener { update() }
    }
  }

  override fun onResume() {
    super.onResume()
    mealsViewModel.meals.observe(this, mealsObserver)
    mealsViewModel.work.observe(this, workObserver)
  }

  override fun onStop() {
    super.onStop()
    mealsViewModel.meals.removeObserver(mealsObserver)
    mealsViewModel.work.removeObserver(workObserver)
  }

  private fun update() {
    mealsViewModel.loadMeals()
  }

  private fun setupMealsList() {
    binding.meals.layoutManager =
      GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
    val decorator = GridItemDecorator(
      resources.getDimensionPixelSize(R.dimen.meal_vrt),
      resources.getDimensionPixelSize(R.dimen.meal_hrz),
      3
    )
    binding.meals.addItemDecoration(decorator)

    adapter.onItemClickListener = {
      Toast.makeText(requireContext(), "Meal ${it.name}", Toast.LENGTH_SHORT).show()
    }
  }

}