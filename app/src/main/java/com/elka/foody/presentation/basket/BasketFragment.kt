package com.elka.foody.presentation.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elka.foody.R
import com.elka.foody.databinding.BasketFragmentBinding
import com.elka.foody.databinding.MealsFragmentBinding
import com.elka.foody.domain.basket.BasketItem
import com.elka.foody.domain.meals.Meal
import com.elka.foody.domain.meals.Tag
import com.elka.foody.presentation.GridItemDecorator
import com.elka.foody.presentation.LinearItemDecorator
import com.elka.foody.presentation.tags.TagAdapter
import com.elka.foody.utils.WithDate
import com.elka.foody.utils.WithLocation
import com.elka.foody.utils.Work
import com.elka.foody.utils.hasLoads

class BasketFragment : Fragment() {
  private lateinit var binding: BasketFragmentBinding
  private val basketViewModel by lazy { ViewModelProvider(this)[BasketViewModel::class.java] }
  private val basketAdapter by lazy { BasketAdapter() }

  private val location by lazy { WithLocation(requireContext()) }

  private val works = listOf(Work.LOAD_BASKET, Work.CHANGE_COUNT_OF_MEALS, Work.ADD_MEAL)

  private val hasLoads: Boolean
    get() {
      val w = basketViewModel.work.value!!.toMutableList()
      return hasLoads(works, w)
    }

  private val workObserver = Observer<List<Work>> {
    binding.swiper.isRefreshing = hasLoads
  }

  private val basketItemsObserver = Observer<List<BasketItem>> {
    basketAdapter.submitList(it)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = BasketFragmentBinding.inflate(inflater, container, false)
    binding.apply {
      lifecycleOwner = viewLifecycleOwner
      adapter = this@BasketFragment.basketAdapter
      viewModel = this@BasketFragment.basketViewModel
    }
    setupMealsList()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    update()

    with(binding.swiper) {
      val color = requireContext().getColor(R.color.swiper_color)
      setColorSchemeColors(color)
      setOnRefreshListener { update() }
    }
  }

  override fun onResume() {
    super.onResume()
    basketViewModel.basketItems.observe(this, basketItemsObserver)
    basketViewModel.work.observe(this, workObserver)
  }

  override fun onStop() {
    super.onStop()
    basketViewModel.basketItems.removeObserver(basketItemsObserver)
    basketViewModel.work.removeObserver(workObserver)
  }

  private fun update() {
    basketViewModel.loadItems()
    updateLocationDate()
  }

  private fun updateLocationDate() {
    binding.date = WithDate.getDateString()
    location.requestLocation({ city ->
      binding.location = city
    }) {
      binding.location = getString(R.string.gps_off)
    }
  }

  private fun setupMealsList() {
    val decorator = LinearItemDecorator(
      resources.getDimensionPixelSize(R.dimen.basket_items_vrt),
      LinearLayoutManager.VERTICAL
    )
    binding.basketItems.addItemDecoration(decorator)

    basketAdapter.onItemClickListener = { meal, count ->
      basketViewModel.changeCountOfMeals(meal, count)
    }
  }
}