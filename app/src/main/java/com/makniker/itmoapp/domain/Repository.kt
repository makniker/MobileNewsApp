package com.makniker.itmoapp.domain

import com.makniker.itmoapp.data.cache.Article
import com.makniker.itmoapp.data.cache.NewsDao
import com.makniker.itmoapp.data.network.ApiService
import com.makniker.itmoapp.presentation.NewsArticleUI
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val dao: NewsDao) {
    suspend fun fetchNews(query: String): Result<List<NewsArticleUI>> {
        val networkResult = runCatching {
            apiService.fetchNews(query).articles.map { article ->
                NewsArticleUI(
                    title = article.title ?: "Title not found",
                    description = article.description ?: "Description not found",
                    urlToImage = article.urlToImage ?: "Image not found",
                    author = article.author ?: "Author not found",
                )
            }
        }
        networkResult.mapCatching {
            if (networkResult.isSuccess) {
                val newList = it.map { article ->
                    Article(
                        0,
                        article.title,
                        article.author,
                        article.description,
                        article.urlToImage,
                    )
                }
                dao.insertAll(newList)
            }
        }
        if (networkResult.isFailure && dao.getAll().isNotEmpty()) {
            val obtainedList = dao.getAll().map { article ->
                NewsArticleUI(
                    title = article.title ?: "Title not found",
                    description = article.description ?: "Description not found",
                    urlToImage = article.urlToImage ?: "Image not found",
                    author = article.author ?: "Author not found",
                )
            }
            val newList = obtainedList.toMutableList()
            newList.add(
                0,
                NewsArticleUI(
                    title = "This list fetched from database",
                    description = "",
                    urlToImage = "",
                    author = ""
                )
            )
            return Result.success(newList)
        }
        return networkResult
    }

}