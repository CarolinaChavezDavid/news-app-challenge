package com.example.news_app_challenge.data.repositories

import com.example.news_app_challenge.data.UsersData
import com.example.news_app_challenge.data.services.UserApiServices
import javax.inject.Inject

class UsersRepositoryImpl
    @Inject
    constructor(private val usersServices: UserApiServices) : UsersRepository {
        override suspend fun getUsersList(): List<UsersData> {
            return usersServices.getUsersPlaceHolder()
        }
    }
