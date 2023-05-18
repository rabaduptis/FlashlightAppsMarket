package com.root14.flashlightappsmarket.view.ui.applicationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.R
import com.root14.flashlightappsmarket.data.QueryType
import com.root14.flashlightappsmarket.data.SortType
import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert
import com.root14.flashlightappsmarket.databinding.FragmentApplicationBinding
import com.root14.flashlightappsmarket.model.AppItem
import com.root14.flashlightappsmarket.model.CategoryType
import com.root14.flashlightappsmarket.viewmodel.ApplicationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Created by ilkay on 17,May, 2023
 */

/**
 * list applications
 */
@AndroidEntryPoint
class ApplicationFragment : Fragment() {
    private lateinit var binding: FragmentApplicationBinding
    private lateinit var appAdapter: AppAdapter


    private val args: ApplicationFragmentArgs by navArgs()
    private val applicationFragmentViewModel: ApplicationFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applicationFragmentViewModel.fetchDB()
        (activity as AppCompatActivity).supportActionBar?.title = args.categoryType.toString()

        updateUi(args)//list all apps

        //search bar
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                updateUi(args = args, QueryType.PARAMETERIZED, p0.toString())
                println("search query $p0")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0.isNullOrBlank()) {
                    applicationFragmentViewModel.fetchDB()
                    updateUi(args)
                }
                return true
            }

        })

        //spinner
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                applicationFragmentViewModel.viewModelScope.launch {
                    when (p2) {
                        0 -> {
                            applicationFragmentViewModel.getAllSosAlerts()
                        }

                        1 -> {
                            //rating value
                            when (args.categoryType) {
                                CategoryType.FLASHLIGHTS -> {
                                    applicationFragmentViewModel.getAllFlashlights(SortType.VALUE)
                                }

                                CategoryType.SOSALERTS -> {
                                    applicationFragmentViewModel.getAllSosAlerts(SortType.VALUE)
                                }

                                CategoryType.COLOREDLIGHTS -> {
                                    applicationFragmentViewModel.getAllSosAlerts(SortType.VALUE)
                                }

                                CategoryType.DEFAULT -> {}
                            }
                        }

                        2 -> {
                            //raiting count
                            when (args.categoryType) {
                                CategoryType.FLASHLIGHTS -> {
                                    applicationFragmentViewModel.getAllFlashlights(SortType.COUNT)
                                }

                                CategoryType.SOSALERTS -> {
                                    applicationFragmentViewModel.getAllSosAlerts(SortType.COUNT)
                                }

                                CategoryType.COLOREDLIGHTS -> {
                                    applicationFragmentViewModel.getAllSosAlerts(SortType.COUNT)
                                }

                                CategoryType.DEFAULT -> {}
                            }
                        }
                    }
                    updateUi(args)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun updateUi(
        args: ApplicationFragmentArgs,
        queryType: QueryType = QueryType.UNPARAMETER,
        searchQuery: String = ""
    ) {
        applicationFragmentViewModel.viewModelScope.launch {

            if (queryType == QueryType.UNPARAMETER) {
                when (args.categoryType) {
                    CategoryType.FLASHLIGHTS -> {
                        applicationFragmentViewModel.getAllFlashlightsRes.observe(viewLifecycleOwner) {
                            setupAppAdapter(it.flashlightToAppItemList())
                        }
                    }

                    CategoryType.COLOREDLIGHTS -> {
                        applicationFragmentViewModel.getAllColorLightRes.observe(viewLifecycleOwner) {
                            setupAppAdapter(it.coloredLight2AppItemList())
                        }
                    }

                    CategoryType.SOSALERTS -> {
                        applicationFragmentViewModel.getAllSosAlertsRes.observe(viewLifecycleOwner) {
                            setupAppAdapter(it.sosAlert2AppItemList())
                        }
                    }

                    CategoryType.DEFAULT -> {

                    }
                }
            } else if (queryType == QueryType.PARAMETERIZED) {
                when (args.categoryType) {
                    CategoryType.FLASHLIGHTS -> {
                        applicationFragmentViewModel.flashLightSearchByName(searchQuery)
                        applicationFragmentViewModel.flashLightSearchByName.observe(
                            viewLifecycleOwner
                        ) {
                            setupAppAdapter(it.flashlightToAppItemList())
                        }
                    }

                    CategoryType.COLOREDLIGHTS -> {
                        applicationFragmentViewModel.colorLightSearchByName(searchQuery)
                        applicationFragmentViewModel.colorLightSearchByName.observe(
                            viewLifecycleOwner
                        ) {
                            setupAppAdapter(it.coloredLight2AppItemList())
                        }
                    }

                    CategoryType.SOSALERTS -> {
                        applicationFragmentViewModel.sosAlertsSearchByName(searchQuery)
                        applicationFragmentViewModel.sosAlertsSearchByName.observe(
                            viewLifecycleOwner
                        ) {
                            setupAppAdapter(it.sosAlert2AppItemList())
                        }
                    }

                    CategoryType.DEFAULT -> {

                    }
                }
            }
        }

    }

    override fun onDestroy() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        super.onDestroy()
    }

    private fun setupAppAdapter(items: List<AppItem>) {
        appAdapter = AppAdapter(items) {
            applicationFragmentViewModel.checkApp(it.packageName, binding.root.context)
        }
        binding.rvApps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvApps.adapter = appAdapter
    }
}

private fun List<Flashlight>.flashlightToAppItemList(): List<AppItem> {
    return this.map { flashlight ->
        AppItem(
            icon = flashlight.iconUrl.toString(),
            name = flashlight.name.toString(),
            ratingValue = flashlight.ratingValue.toString(),
            ratingCount = flashlight.ratingCount.toString(),
            downloadCount = flashlight.downloads.toString(),
            packageName = flashlight.packageName.toString()
        )
    }
}

private fun List<ColoredLight>.coloredLight2AppItemList(): List<AppItem> {
    return this.map { flashlight ->
        AppItem(
            icon = flashlight.iconUrl.toString(),
            name = flashlight.name.toString(),
            ratingValue = flashlight.ratingValue.toString(),
            ratingCount = flashlight.ratingCount.toString(),
            downloadCount = flashlight.downloads.toString(),
            packageName = flashlight.packageName.toString()
        )
    }
}

private fun List<SOSAlert>.sosAlert2AppItemList(): List<AppItem> {
    return this.map { flashlight ->
        AppItem(
            icon = flashlight.iconUrl.toString(),
            name = flashlight.name.toString(),
            ratingValue = flashlight.ratingValue.toString(),
            ratingCount = flashlight.ratingCount.toString(),
            downloadCount = flashlight.downloads.toString(),
            packageName = flashlight.packageName.toString()
        )
    }
}



