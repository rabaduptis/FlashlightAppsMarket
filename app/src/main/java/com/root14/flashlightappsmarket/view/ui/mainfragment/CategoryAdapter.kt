package com.root14.flashlightappsmarket.view.ui.mainfragment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.model.CategoryItem

/**
 * Created by ilkay on 17,May, 2023
 */
class CategoryAdapter(private val categoryItem: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryItem[position]
        holder.bind(categoryItem)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}