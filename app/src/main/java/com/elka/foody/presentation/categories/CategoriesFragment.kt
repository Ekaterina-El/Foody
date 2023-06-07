package com.elka.foody.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elka.foody.R
import com.elka.foody.databinding.CategoriesFragmentBinding
import com.elka.foody.domain.categories.Category
import com.elka.foody.presentation.LinearItemDecorator
import com.elka.foody.utils.WithDate
import com.elka.foody.utils.WithLocation
import com.elka.foody.utils.Work
import com.elka.foody.utils.hasLoads

class CategoriesFragment : Fragment() {
  private lateinit var binding: CategoriesFragmentBinding
  private val categoriesViewModel by lazy { ViewModelProvider(this)[CategoriesViewModel::class.java] }

  private val adapter by lazy { CategoriesAdapter() }
  private val location by lazy { WithLocation(requireContext()) }

  private val works = listOf(
    Work.LOAD_CATEGORIES,
  )

  private val hasLoads: Boolean
    get() {
      val w = categoriesViewModel.work.value!!.toMutableList()
      return hasLoads(works, w)
    }


  private val workObserver = Observer<List<Work>> {
    binding.swiper.isRefreshing = hasLoads
  }

  private val categoriesObserver = Observer<List<Category>> {
    adapter.submitList(it)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = CategoriesFragmentBinding.inflate(inflater, container, false)
    binding.apply {
      lifecycleOwner = viewLifecycleOwner
      adapter = this@CategoriesFragment.adapter
    }
    setupCategoriesList()
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
    categoriesViewModel.categories.observe(this, categoriesObserver)
    categoriesViewModel.work.observe(this, workObserver)
  }

  override fun onStop() {
    super.onStop()
    categoriesViewModel.categories.removeObserver(categoriesObserver)
    categoriesViewModel.work.removeObserver(workObserver)
  }

  private fun update() {
    categoriesViewModel.loadCategories()
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


  private fun setupCategoriesList() {
    val decorator = LinearItemDecorator(resources.getDimensionPixelSize(R.dimen.categories_horz), LinearLayoutManager.VERTICAL)
    binding.categories.addItemDecoration(decorator)

    adapter.onItemClickListener = {
      Toast.makeText(requireContext(), "Category ${it.name}", Toast.LENGTH_SHORT).show()
    }
  }
}