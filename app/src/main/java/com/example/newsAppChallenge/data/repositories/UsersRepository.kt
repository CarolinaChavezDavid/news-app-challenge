package com.example.newsAppChallenge.data.repositories

import com.example.newsAppChallenge.data.UsersData
import javax.inject.Singleton

@Singleton
interface UsersRepository {
    suspend fun getUsersList(): List<UsersData>

    suspend fun getUserDetail(userId: String): UsersData
}