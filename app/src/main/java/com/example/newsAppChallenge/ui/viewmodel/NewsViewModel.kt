package com.example.newsAppChallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.NewsExample
import com.example.newsAppChallenge.data.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
    @Inject
    constructor(private val newsRepository: NewsRepository) : ViewModel() {
        private var _newsDetail = MutableStateFlow<NewsData>(NewsExample)
        val newsDetail: StateFlow<NewsData> = _newsDetail

        private var _newsList = MutableStateFlow<List<NewsData>>(emptyList())
        val newsList: StateFlow<List<NewsData>> = _newsList

        private var _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
        val uiState: StateFlow<NewsUiState> = _uiState

        fun getNews() {
            viewModelScope.launch {
                _uiState.value = NewsUiState.Loading

                try {
                    _uiState.value = NewsUiState.Success
                    _newsList.value = newsRepository.getNewsPlaceHolder()
                    Log.i("PrintRESULT", _newsList.value.toString())
                } catch (e: IOException) {
                    _uiState.value = NewsUiState.Error
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
                } catch (e: IOException) {
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
