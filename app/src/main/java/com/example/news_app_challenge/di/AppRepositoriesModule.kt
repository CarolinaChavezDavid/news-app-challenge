package com.example.news_app_challenge.di

import com.example.news_app_challenge.data.repositories.NewsRepository
import com.example.news_app_challenge.data.repositories.NewsRepositoryImpl
import com.example.news_app_challenge.data.repositories.UsersRepository
import com.example.news_app_challenge.data.repositories.UsersRepositoryImpl
import com.example.news_app_challenge.data.services.NewsApiServices
import com.example.news_app_challenge.data.services.UserApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppRepositoriesModule {
    @Provides
    fun provideNewsRepository(newsServices: NewsApiServices): NewsRepository = NewsRepositoryImpl(newsServices)

    @Provides
    fun provideUsersRepository(userServices: UserApiServices): UsersRepository = UsersRepositoryImpl(userServices)
}
