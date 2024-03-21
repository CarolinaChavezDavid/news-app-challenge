package com.example.newsAppChallenge.data.services

import com.example.newsAppChallenge.data.UsersData
import retrofit2.http.GET

interface UserApiServices {
    @GET("users/")
    suspend fun getUsersPlaceHolder(): List<UsersData>
}
