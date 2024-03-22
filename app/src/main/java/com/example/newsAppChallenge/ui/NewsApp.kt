package com.example.newsAppChallenge.ui

import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun NewsApp(
    viewModel: NewsViewModel = hiltViewModel(),
    onClickItem: (newsId: String) -> Unit,
) {
    viewModel.getNews()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (uiState) {
                is NewsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is NewsUiState.Success ->
                    NewsScreen(
                        news = viewModel.newsList.collectAsState().value,
                        contentPadding = it,
                        onClickItem = onClickItem,
                    )
                is NewsUiState.Error -> ErrorScreen({ }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
