package com.example.news_app_challenge.data.repositories

import com.example.news_app_challenge.data.UsersData

interface UsersRepository {
    suspend fun getUsersList(): List<UsersData>
}