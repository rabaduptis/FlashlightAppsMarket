package com.root14.flashlightappsmarket.view.ui.mainFragment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.model.CategoryItem

/**
 * Created by ilkay on 17,May, 2023
 */
class CategoryAdapter(
    private val categoryItems: List<CategoryItem>,
    private val onItemClick: (categoryItem: CategoryItem) -> Unit
) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryItems[position]
        holder.bind(categoryItem)
        holder.itemView.setOnClickListener {
            onItemClick(categoryItem)
        }
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }
}