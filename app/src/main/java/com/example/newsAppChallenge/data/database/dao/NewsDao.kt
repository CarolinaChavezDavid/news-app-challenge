package com.example.newsAppChallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsAppChallenge.data.database.Entities.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    suspend fun getAllNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsEntity>)

    @Query("DELETE FROM news_table")
    suspend fun deleteAll()
}
