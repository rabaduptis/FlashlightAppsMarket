package com.root14.flashlightappsmarket.network.api

import com.root14.flashlightappsmarket.network.models.response.AppResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("JsonStub-User-Key: f5e0861a-b53d-4b80-9c28-2233780c3d5d")
    @GET("flashlights")
    suspend fun flashlights(): Response<List<AppResponse>>

    @Headers("JsonStub-User-Key: f5e0861a-b53d-4b80-9c28-2233780c3d5d")
    @GET("colorlights")
    suspend fun colorlights(): Response<List<AppResponse>>

    @Headers("JsonStub-User-Key: f5e0861a-b53d-4b80-9c28-2233780c3d5d")
    @GET("sosalerts")
    suspend fun sosalerts(): Response<List<AppResponse>>
}