package com.makniker.itmoapp.data.network

import com.google.gson.annotations.SerializedName

data class NewsArticleDTO(
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val urlToImage: String?, )