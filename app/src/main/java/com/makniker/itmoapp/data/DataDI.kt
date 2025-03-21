package com.makniker.itmoapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.makniker.itmoapp.data.cache.ArticleDatabase
import com.makniker.itmoapp.data.cache.NewsDao
import com.makniker.itmoapp.data.network.ApiInterceptor
import com.makniker.itmoapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataDI {
    private val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    fun provideRetrofit(): ApiService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).client(OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()).build().create(
        ApiService::class.java
    )

    @Provides
    fun provideRoom(@ApplicationContext appContext: Context): NewsDao = Room.databaseBuilder(
        appContext, ArticleDatabase::class.java, "database-name"
    ).build().newsDao()
}