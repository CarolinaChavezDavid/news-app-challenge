package com.example.news_app_challenge.data.repositories

import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.data.NewsTopHeadlinesData
import com.example.news_app_challenge.data.services.NewsApiServices
import javax.inject.Inject

class NewsRepositoryImpl
    @Inject
    constructor(private val newsAPIServices: NewsApiServices) :
    NewsRepository {
        override suspend fun getNewsTopHeadlines(category: String): List<NewsTopHeadlinesData> =
            newsAPIServices.getTopHeadlinesNews(category, "")

        override suspend fun getNewsPlaceHolder(): List<NewsData> {
            return newsAPIServices.getNewsPlaceHolder()
        }
    }
