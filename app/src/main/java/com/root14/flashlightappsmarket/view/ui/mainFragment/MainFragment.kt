package com.root14.flashlightappsmarket.view.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.R
import com.root14.flashlightappsmarket.databinding.FragmentMainBinding
import com.root14.flashlightappsmarket.model.CategoryItem
import com.root14.flashlightappsmarket.model.CategoryType
import com.root14.flashlightappsmarket.view.ui.applicationFragment.ApplicationFragment
import com.root14.flashlightappsmarket.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * main fragment listing categories
 */
@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var categoryAdapter: CategoryAdapter

    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList = createCategoryList()
        categoryAdapter = CategoryAdapter(categoryList) { categoryItem ->
            println("clicked ${categoryItem.name}")
            println("type ${categoryItem.type}")

            val action =
                MainFragmentDirections.actionMainFragmentToApplicationFragment()
            action.categoryType = categoryItem.type
            findNavController().navigate(action)


        }
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = categoryAdapter

        mainFragmentViewModel.fetchAPI()
    }

    //test
    private fun createCategoryList() = listOf(
        CategoryItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_flashlight_on_24) },
            "Flashlights",
            CategoryType.FLASHLIGHTS
        ),
        CategoryItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_light_mode_24) },
            "Colored Lights",
            CategoryType.COLOREDLIGHTS
        ),
        CategoryItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_sos_24) },
            "Sos Alerts",
            CategoryType.SOSALERTS
        )
    )
}