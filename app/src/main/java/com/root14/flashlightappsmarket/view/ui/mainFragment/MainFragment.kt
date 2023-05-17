package com.root14.flashlightappsmarket.view.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.R
import com.root14.flashlightappsmarket.databinding.FragmentMainBinding
import com.root14.flashlightappsmarket.model.CategoryItem
import com.root14.flashlightappsmarket.model.CategoryType

/**
 * main fragment listing categories
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
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

        val navController = findNavController()

        val navOptions = NavOptions.Builder()
            .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
            .build()

        val categoryList = createCategoryList()
        categoryAdapter = CategoryAdapter(categoryList) { categoryItem ->
            println("clicked ${categoryItem.name}")
            println("type ${categoryItem.type}")
            navController.navigate(R.id.applicationFragment, null, navOptions)

        }
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = categoryAdapter

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