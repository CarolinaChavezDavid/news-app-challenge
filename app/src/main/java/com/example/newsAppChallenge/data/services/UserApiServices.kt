package com.example.newsAppChallenge.data.services

import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.UsersData
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiServices {
    @GET("users/")
    suspend fun getUsersPlaceHolder(): List<UsersData>

    @GET("users/{id}/")
    suspend fun getUsersDetailPlaceHolder(
        @Path("id") userId: String,
    ): UsersData
}
