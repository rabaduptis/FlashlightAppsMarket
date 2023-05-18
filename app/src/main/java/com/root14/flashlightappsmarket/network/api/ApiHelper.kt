package com.root14.flashlightappsmarket.network.api

import com.root14.flashlightappsmarket.network.models.response.AppResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun flashlights(): Response<List<AppResponse>>
    suspend fun colorlights(): Response<List<AppResponse>>
    suspend fun sosalerts(): Response<List<AppResponse>>
}