package com.root14.flashlightappsmarket.view.ui.applicationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.R
import com.root14.flashlightappsmarket.databinding.FragmentApplicationBinding
import com.root14.flashlightappsmarket.model.AppItem
/**
 * Created by ilkay on 17,May, 2023
 */

/**
 * list applications
 */
class ApplicationFragment : Fragment() {

    private lateinit var binding: FragmentApplicationBinding
    private lateinit var appAdapter: AppAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val applist = createAppList()
        appAdapter = AppAdapter(applist)
        binding.rvApps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvApps.adapter = appAdapter
    }


    //for test
    private fun createAppList() = listOf(
        AppItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_photo_24) },
            "app1",
            4.5f,
            100,
            5000
        ),
        AppItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_photo_24) },
            "app1",
            4.5f,
            100,
            5000
        ),
        AppItem(
            context?.let { ContextCompat.getDrawable(it, R.drawable.baseline_photo_24) },
            "app1",
            4.5f,
            100,
            5000
        ),
    )
}