package com.root14.flashlightappsmarket.view.ui.applicationFragment

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.root14.flashlightappsmarket.databinding.ItemAppBinding
import com.root14.flashlightappsmarket.model.AppItem


/**
 * Created by ilkay on 17,May, 2023
 */
class AppViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appItem: AppItem) {
        // binding.imageViewIcon.setImageDrawable(appItem.icon)
        binding.textViewName.text = appItem.name
        binding.textViewRatingValue.text = appItem.ratingValue.toString()
        binding.textViewRatingCount.text = appItem.ratingCount.toString()
        binding.textViewDownloadCount.text = appItem.downloadCount.toString()
    }

    companion object {
        fun create(parent: ViewGroup): AppViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemAppBinding.inflate(inflater, parent, false)
            return AppViewHolder(binding)
        }
    }
}