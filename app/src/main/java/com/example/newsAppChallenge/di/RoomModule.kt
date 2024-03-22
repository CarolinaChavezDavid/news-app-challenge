package com.example.newsAppChallenge.di

import android.content.Context
import androidx.room.Room
import com.example.newsAppChallenge.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val NEWS_DATABASE_NAME = "news_database"

    @Singleton
    @Provides
    fun providesRoom(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, NewsDatabase::class.java, NEWS_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.getNewsDao()
}
