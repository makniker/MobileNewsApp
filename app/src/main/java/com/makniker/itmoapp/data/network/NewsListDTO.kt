package com.makniker.itmoapp.data.network

import com.google.gson.annotations.SerializedName

data class NewsListDTO(
    @SerializedName("status") val status: String?,
    @SerializedName("articles") val articles: List<NewsArticleDTO>,

    )