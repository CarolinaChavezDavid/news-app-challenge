package com.example.news_app_challenge.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_app_challenge.ui.components.AppHeader
import com.example.news_app_challenge.ui.screens.NewsScreen
import com.example.news_app_challenge.ui.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(viewModel: NewsViewModel = hiltViewModel()) {
    viewModel.getNews()
    val uiState by viewModel.newsState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppHeader() },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            NewsScreen(
                newsUiState = uiState,
                contentPadding = it,
            )
        }
    }
}
