package com.example.newsAppChallenge.di

import com.example.newsAppChallenge.data.database.dao.NewsDao
import com.example.newsAppChallenge.data.services.NewsApiServices
import com.example.newsAppChallenge.data.services.UserApiServices
import com.example.newsAppChallenge.domain.repositories.NewsRepository
import com.example.newsAppChallenge.domain.repositories.NewsRepositoryImpl
import com.example.newsAppChallenge.domain.repositories.UsersRepository
import com.example.newsAppChallenge.domain.repositories.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppRepositoriesModule {
    @Provides
    fun provideNewsRepository(
        newsServices: NewsApiServices,
        newsDao: NewsDao,
    ): NewsRepository = NewsRepositoryImpl(newsServices, newsDao)

    @Provides
    fun provideUsersRepository(userServices: UserApiServices): UsersRepository = UsersRepositoryImpl(userServices)
}
