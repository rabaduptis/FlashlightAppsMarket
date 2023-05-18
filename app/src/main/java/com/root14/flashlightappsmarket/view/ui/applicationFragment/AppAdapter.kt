package com.root14.flashlightappsmarket.view.ui.applicationFragment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.model.AppItem
import com.root14.flashlightappsmarket.model.CategoryItem

/**
 * Created by ilkay on 17,May, 2023
 */
class AppAdapter(
    private val appList: List<AppItem>,
    private val onItemClick: (appItem: AppItem) -> Unit
) : RecyclerView.Adapter<AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appItem = appList[position]
        holder.bind(appItem)
        holder.itemView.setOnClickListener { onItemClick(appItem) }
    }

    override fun getItemCount(): Int {
        return appList.size
    }
}