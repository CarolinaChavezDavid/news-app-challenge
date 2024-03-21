package com.example.news_app_challenge.data.services

import com.example.news_app_challenge.data.UsersData
import retrofit2.http.GET

interface UserApiServices {
    @GET("users/")
    suspend fun getUsersPlaceHolder(): List<UsersData>
}
