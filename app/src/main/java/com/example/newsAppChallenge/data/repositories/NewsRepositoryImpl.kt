package com.example.newsAppChallenge.data.repositories

import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.NewsTopHeadlinesData
import com.example.newsAppChallenge.data.services.NewsApiServices
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

        override suspend fun getNewsDetailPlaceHolder(newsId: String): NewsData {
            return newsAPIServices.getNewsDetailPlaceHolder(newsId)
        }
    }
