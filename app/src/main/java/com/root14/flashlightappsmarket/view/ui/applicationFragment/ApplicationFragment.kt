package com.root14.flashlightappsmarket.view.ui.applicationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.data.entity.ColoredLight
import com.root14.flashlightappsmarket.data.entity.Flashlight
import com.root14.flashlightappsmarket.data.entity.SOSAlert
import com.root14.flashlightappsmarket.databinding.FragmentApplicationBinding
import com.root14.flashlightappsmarket.model.AppItem
import com.root14.flashlightappsmarket.model.CategoryType
import com.root14.flashlightappsmarket.viewmodel.ApplicationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


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
                applicationFragmentViewModel.getAllSosAlerts.observe(viewLifecycleOwner) {
                    setupAppAdapter(it.sosAlert2AppItemList())
                }
            }

            CategoryType.DEFAULT -> {

            }
        }
    }

    private fun setupAppAdapter(items: List<AppItem>) {
        appAdapter = AppAdapter(items)
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
            downloadCount = flashlight.downloads.toString()
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
            downloadCount = flashlight.downloads.toString()
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
            downloadCount = flashlight.downloads.toString()
        )
    }
}



