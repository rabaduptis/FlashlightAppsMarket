package com.root14.flashlightappsmarket.view.ui.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.root14.flashlightappsmarket.R
import com.root14.flashlightappsmarket.databinding.FragmentMainBinding
import com.root14.flashlightappsmarket.model.AppItem
import com.root14.flashlightappsmarket.view.AppAdapter

/**
 * main fragment listing categories
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var appAdapter: AppAdapter
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

    }
}