package com.root14.flashlightappsmarket.network.api

import com.root14.flashlightappsmarket.network.models.response.AppResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun flashlights(): Response<List<AppResponse>> = apiService.login()
    override suspend fun colorlights(): Response<List<AppResponse>> = apiService.register()
    override suspend fun sosalerts(): Response<List<AppResponse>> = apiService.loadFeed()
}