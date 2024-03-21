package com.example.newsAppChallenge.data.repositories

import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.NewsTopHeadlinesData

interface NewsRepository {
    suspend fun getNewsTopHeadlines(category: String): List<NewsTopHeadlinesData>

    suspend fun getNewsPlaceHolder(): List<NewsData>

    suspend fun getNewsDetailPlaceHolder(newsId: String): NewsData
}
