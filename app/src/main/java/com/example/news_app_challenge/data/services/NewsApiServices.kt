package com.example.news_app_challenge.data.services

import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.data.NewsTopHeadlinesData
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {
    @GET("top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("category") category: String,
        @Query("apikey") apiKey: String,
    ): List<NewsTopHeadlinesData>

    @GET("posts/")
    suspend fun getNewsPlaceHolder(): List<NewsData>
}
