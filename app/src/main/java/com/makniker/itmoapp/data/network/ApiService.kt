package com.makniker.itmoapp.data.network

import com.makniker.itmoapp.data.network.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun fetchNews(@Query("q") query: String): NewsListDTO
}