package com.root14.flashlightappsmarket.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.model.AppItem

class AppAdapter(private val appList: List<AppItem>) : RecyclerView.Adapter<AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appItem = appList[position]
        holder.bind(appItem)
    }

    override fun getItemCount(): Int {
        return appList.size
    }
}