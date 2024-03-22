package com.example.newsAppChallenge.domain.repositories

import com.example.newsAppChallenge.data.database.Entities.NewsEntity
import com.example.newsAppChallenge.data.database.dao.NewsDao
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.NewsTopHeadlinesData
import com.example.newsAppChallenge.data.services.NewsApiServices
import com.example.newsAppChallenge.domain.model.News
import com.example.newsAppChallenge.domain.model.toDomain
import javax.inject.Inject

class NewsRepositoryImpl
    @Inject
    constructor(private val newsAPIServices: NewsApiServices, private val newsDao: NewsDao) :
    NewsRepository {
        override suspend fun getNewsTopHeadlines(category: String): List<NewsTopHeadlinesData> =
            newsAPIServices.getTopHeadlinesNews(category, "")

        override suspend fun getNewsPlaceHolderApi(): List<News> {
            return newsAPIServices.getNewsPlaceHolder().map { it.toDomain() }
        }

        override suspend fun getNewsFromDatabase(): List<News> {
            return newsDao.getAllNews().map { it.toDomain() }
        }

        override suspend fun insertNews(news: List<NewsEntity>) {
            newsDao.insertAll(news)
        }

        override suspend fun clearNews() {
            newsDao.deleteAll()
        }

        override suspend fun getNewsDetailPlaceHolder(newsId: String): NewsData {
            return newsAPIServices.getNewsDetailPlaceHolder(newsId)
        }
    }
