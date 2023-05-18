package com.root14.flashlightappsmarket.network.repo

import com.root14.flashlightappsmarket.network.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun flashlights() = apiHelper.flashlights()
    suspend fun colorlights() = apiHelper.colorlights()
    suspend fun sosalerts()= apiHelper.sosalerts()
}