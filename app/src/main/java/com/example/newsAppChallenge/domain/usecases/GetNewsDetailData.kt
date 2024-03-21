package com.example.newsAppChallenge.domain.usecases

import android.service.autofill.UserData
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.repositories.NewsRepository
import com.example.newsAppChallenge.data.repositories.UsersRepository
import javax.inject.Inject

class GetNewsDetailData @Inject constructor(private val newsRepository: NewsRepository, usersRepository: UsersRepository, newsId: String) {

}