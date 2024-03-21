package com.example.newsAppChallenge.di

import com.example.newsAppChallenge.data.repositories.NewsRepository
import com.example.newsAppChallenge.data.repositories.NewsRepositoryImpl
import com.example.newsAppChallenge.data.repositories.UsersRepository
import com.example.newsAppChallenge.data.repositories.UsersRepositoryImpl
import com.example.newsAppChallenge.data.services.NewsApiServices
import com.example.newsAppChallenge.data.services.UserApiServices
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
