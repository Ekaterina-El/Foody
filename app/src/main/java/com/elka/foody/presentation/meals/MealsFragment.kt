package com.elka.foody.presentation.meals

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
import com.elka.foody.databinding.MealsFragmentBinding
import com.elka.foody.domain.meals.Meal
import com.elka.foody.domain.meals.Tag
import com.elka.foody.presentation.GridItemDecorator
import com.elka.foody.presentation.LinearItemDecorator
import com.elka.foody.presentation.tags.TagAdapter
import com.elka.foody.utils.Work
import com.elka.foody.utils.hasLoads

class MealsFragment : Fragment() {
  private lateinit var binding: MealsFragmentBinding
  private val mealsViewModel by lazy { ViewModelProvider(this)[MealsViewModel::class.java] }

  private val mealsAdapter by lazy { MealsAdapter() }
  private val tagAdapter by lazy {
    TagAdapter {
      mealsViewModel.setActiveTag(it)
    }
  }

  private val works = listOf(Work.LOAD_MEALS)

  private val hasLoads: Boolean
    get() {
      val w = mealsViewModel.work.value!!.toMutableList()
      return hasLoads(works, w)
    }


  private val workObserver = Observer<List<Work>> {
    binding.swiper.isRefreshing = hasLoads
  }

  private val tagsObserver = Observer<List<Tag>> {
    tagAdapter.submitList(it)
  }

  private val mealsObserver = Observer<List<Meal>> {
    mealsAdapter.submitList(it)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = MealsFragmentBinding.inflate(inflater, container, false)
    binding.apply {
      master = this@MealsFragment
      lifecycleOwner = viewLifecycleOwner
      adapter = this@MealsFragment.mealsAdapter
      tagsAdapter = this@MealsFragment.tagAdapter
    }
    setupMealsList()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val args =  MealsFragmentArgs.fromBundle(requireArguments())
    binding.category = args.category

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
    mealsViewModel.tags.observe(this, tagsObserver)
  }

  override fun onStop() {
    super.onStop()
    mealsViewModel.meals.removeObserver(mealsObserver)
    mealsViewModel.work.removeObserver(workObserver)
    mealsViewModel.tags.removeObserver(tagsObserver)
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

    val tagsDecorator = LinearItemDecorator(
      resources.getDimensionPixelSize(R.dimen.tags_horz),
      LinearLayoutManager.HORIZONTAL
    )
    binding.tags.addItemDecoration(tagsDecorator)

    mealsAdapter.onItemClickListener = {
      openMealDialog(it)
    }
  }

  private val mealDialogListener by lazy {
    object : MealDialog.Companion.Listener {
      override fun addToBasket(meal: Meal) {
        mealsViewModel.addToBasket(meal)
        mealDialog.disagree()
      }
    }
  }
  private val mealDialog: MealDialog by lazy {
    MealDialog(requireContext(), mealDialogListener)
  }

  private fun openMealDialog(meal: Meal) {
    mealDialog.open(meal)
  }

  fun goBack() {
    findNavController().popBackStack()
  }
}