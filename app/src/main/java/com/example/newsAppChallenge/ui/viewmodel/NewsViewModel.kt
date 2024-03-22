package com.example.newsAppChallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.newsDataExample
import com.example.newsAppChallenge.domain.model.News
import com.example.newsAppChallenge.domain.repositories.NewsRepository
import com.example.newsAppChallenge.domain.usecases.GetNewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
    @Inject
    constructor(private val newsRepository: NewsRepository, private val useCase: GetNewsUseCases) : ViewModel() {
        private var _newsDetail = MutableStateFlow(newsDataExample)
        val newsDetail: StateFlow<NewsData> = _newsDetail

        private var _newsList = MutableStateFlow<List<News>>(emptyList())
        val newsList: StateFlow<List<News>> = _newsList

        private var _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
        val uiState: StateFlow<NewsUiState> = _uiState

        fun getNews() {
            viewModelScope.launch {
                _uiState.value = NewsUiState.Loading
                val news = useCase()
                _uiState.value =
                    if (news.isNotEmpty()) {
                        _newsList.value = news
                        NewsUiState.Success
                    } else {
                        NewsUiState.Error
                    }
            }
        }

        fun reloadPage() {
            _uiState.value = NewsUiState.Loading
        }

        fun searchNews(query: String) {
            if (_uiState.value == NewsUiState.Success) {
                val foundArticles =
                    _newsList.value.filter { item ->
                        item.title.contains(query)
                    }
                _newsList.value = foundArticles
            } else {
                _newsList.value = emptyList()
            }
        }

        fun getNewsDetail(newsId: String) {
            viewModelScope.launch {
                _uiState.value = NewsUiState.Loading

                try {
                    _uiState.value = NewsUiState.Success
                    _newsDetail.value = newsRepository.getNewsDetailPlaceHolder(newsId)
                } catch (e: RuntimeException) {
                    _uiState.value = NewsUiState.Error
                }
            }
        }
    }

sealed interface NewsUiState {
    data object Success : NewsUiState

    data object Error : NewsUiState

    data object Loading : NewsUiState
}
