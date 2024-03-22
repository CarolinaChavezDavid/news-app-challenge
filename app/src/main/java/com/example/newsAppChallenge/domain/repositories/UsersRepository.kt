package com.example.newsAppChallenge.domain.repositories

import com.example.newsAppChallenge.data.models.UsersData
import javax.inject.Singleton

@Singleton
interface UsersRepository {
    suspend fun getUsersList(): List<UsersData>

    suspend fun getUserDetail(userId: String): UsersData
}