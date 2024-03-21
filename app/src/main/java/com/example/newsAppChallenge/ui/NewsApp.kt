package com.example.newsAppChallenge.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsAppChallenge.ui.screens.ErrorScreen
import com.example.newsAppChallenge.ui.screens.LoadingScreen
import com.example.newsAppChallenge.ui.screens.NewsScreen
import com.example.newsAppChallenge.ui.viewmodel.NewsUiState
import com.example.newsAppChallenge.ui.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(viewModel: NewsViewModel = hiltViewModel(), onClickItem: (newsId: String) -> Unit,
) {
    viewModel.getNews()
    val uiState by viewModel.uiState.collectAsState()
    val newsList by viewModel.newsList.collectAsState()
    Scaffold(
        topBar = { },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (uiState) {
                is NewsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is NewsUiState.Success -> NewsScreen(news = newsList, contentPadding = it, onClickItem = onClickItem)
                is NewsUiState.Error -> ErrorScreen({ }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
