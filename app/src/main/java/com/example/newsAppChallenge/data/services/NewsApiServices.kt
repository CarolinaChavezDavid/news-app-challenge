package com.example.newsAppChallenge.data.services

import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.NewsTopHeadlinesData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiServices {
    @GET("top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("category") category: String,
        @Query("apikey") apiKey: String,
    ): List<NewsTopHeadlinesData>

    @GET("posts/")
    suspend fun getNewsPlaceHolder(): List<NewsData>

    @GET("posts/{id}/")
    suspend fun getNewsDetailPlaceHolder(
        @Path("id") newsId: String,
    ): NewsData
}
