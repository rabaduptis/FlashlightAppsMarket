package com.root14.flashlightappsmarket.view.ui.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.databinding.ListCategoriesBinding
import com.root14.flashlightappsmarket.model.AppItem
import com.root14.flashlightappsmarket.model.CategoryItem

/**
 * Created by ilkay on 17,May, 2023
 */
class CategoryViewHolder(private val binding: ListCategoriesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(categoryItem: CategoryItem) {
        binding.imageViewIcon.setImageDrawable(categoryItem.icon)
        binding.textViewCategoryName.text = categoryItem.name
    }

    companion object {
        fun create(parent: ViewGroup): CategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListCategoriesBinding.inflate(inflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }
}