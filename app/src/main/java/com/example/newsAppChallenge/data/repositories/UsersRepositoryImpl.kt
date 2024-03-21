package com.example.newsAppChallenge.data.repositories

import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.data.services.UserApiServices
import javax.inject.Inject

class UsersRepositoryImpl
    @Inject
    constructor(private val usersServices: UserApiServices) : UsersRepository {
        override suspend fun getUsersList(): List<UsersData> {
            return usersServices.getUsersPlaceHolder()
        }
    }
