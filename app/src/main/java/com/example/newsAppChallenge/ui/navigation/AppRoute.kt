package com.example.newsAppChallenge.ui.navigation

import com.example.newsAppChallenge.ui.navigation.AppDestination.NEWS_APP_SCREEN_ROUTE
import com.example.newsAppChallenge.ui.navigation.AppDestination.NEWS_DETAIL_SCREEN_ROUTE
import com.example.newsAppChallenge.ui.navigation.AppDestination.USERS_DETAIL_SCREEN_ROUTE
import com.example.newsAppChallenge.ui.navigation.AppDestination.USERS_SCREEN_ROUTE

object AppDestination {
    const val NEWS_APP_SCREEN_ROUTE = "NEWS_APP_SCREEN_ROUTE"
    const val NEWS_DETAIL_SCREEN_ROUTE = "NEWS_DETAIL_SCREEN_ROUTE"
    const val USERS_SCREEN_ROUTE = "USER_SCREEN_ROUTE"
    const val USERS_DETAIL_SCREEN_ROUTE = "USERS_DETAIL_SCREEN_ROUTE"
}

sealed class AppScreen(val route: String) {
    object NewsAppScreen : AppScreen(NEWS_APP_SCREEN_ROUTE)

    object NewsDetailScreen : AppScreen(NEWS_DETAIL_SCREEN_ROUTE)

    object UserScreen : AppScreen(USERS_SCREEN_ROUTE)

    object UserLocation : AppScreen(USERS_DETAIL_SCREEN_ROUTE)
}
