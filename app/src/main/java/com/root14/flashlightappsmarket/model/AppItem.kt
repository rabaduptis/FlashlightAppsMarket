package com.root14.flashlightappsmarket.model

import android.graphics.drawable.Drawable

data class AppItem(
    val icon: Drawable?,
    val name: String,
    val ratingValue: Float,
    val ratingCount: Int,
    val downloadCount: Int
)