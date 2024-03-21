package com.example.news_app_challenge.data.repositories

import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.data.NewsTopHeadlinesData

interface NewsRepository {
    suspend fun getNewsTopHeadlines(category: String): List<NewsTopHeadlinesData>

    suspend fun getNewsPlaceHolder(): List<NewsData>
}
