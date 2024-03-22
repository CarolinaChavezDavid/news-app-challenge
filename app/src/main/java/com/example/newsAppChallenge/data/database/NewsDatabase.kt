package com.example.newsAppChallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsAppChallenge.data.database.Entities.NewsEntity
import com.example.newsAppChallenge.data.database.dao.NewsDao

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}
