package com.example.newsAppChallenge.domain.repositories

import com.example.newsAppChallenge.data.database.Entities.NewsEntity
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.NewsTopHeadlinesData
import com.example.newsAppChallenge.domain.model.News

interface NewsRepository {
    suspend fun getNewsTopHeadlines(category: String): List<NewsTopHeadlinesData>

    suspend fun getNewsPlaceHolderApi(): List<News>

    suspend fun getNewsFromDatabase(): List<News>

    suspend fun insertNews(news: List<NewsEntity>)

    suspend fun clearNews()

    suspend fun getNewsDetailPlaceHolder(newsId: String): NewsData
}
