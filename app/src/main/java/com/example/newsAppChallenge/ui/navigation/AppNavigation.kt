package com.example.newsAppChallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsAppChallenge.ui.NewsApp
import com.example.newsAppChallenge.ui.screens.NewsDetailScreen
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.NewsAppScreen.route) {
        composable(route = AppScreen.NewsAppScreen.route) {
            NewsApp(onClickItem = {newsId -> navController.navigate("${AppScreen.NewsDetailScreen.route}/$newsId")})
        }

        composable(
            route = "${AppScreen.NewsDetailScreen.route}/{newsId}",
            arguments = listOf(navArgument("newsId") { type = NavType.StringType }),

            ) { navBackStackEntry ->
            val newsArg = navBackStackEntry.arguments?.getString("newsId")

        }

    }
}