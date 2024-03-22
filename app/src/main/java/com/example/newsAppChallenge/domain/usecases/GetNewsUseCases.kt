package com.example.newsAppChallenge.domain.usecases

import com.example.newsAppChallenge.data.database.Entities.toDatabase
import com.example.newsAppChallenge.domain.model.News
import com.example.newsAppChallenge.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCases
    @Inject
    constructor(private val newsRepository: NewsRepository) {
        suspend operator fun invoke(): List<News> {
            val news = newsRepository.getNewsPlaceHolderApi()

            return if (news.isNotEmpty()) {
                newsRepository.clearNews()
                newsRepository.insertNews(news.map { it.toDatabase() })
                news
            } else {
                newsRepository.getNewsFromDatabase()
            }
        }
    }
