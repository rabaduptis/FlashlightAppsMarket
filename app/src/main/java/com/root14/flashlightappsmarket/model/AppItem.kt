package com.root14.flashlightappsmarket.model

/**
 * Created by ilkay on 17,May, 2023
 */

data class AppItem(
    val icon: String,
    val name: String,
    val ratingValue: String,
    val ratingCount: String,
    val downloadCount: String,
    val packageName: String
)