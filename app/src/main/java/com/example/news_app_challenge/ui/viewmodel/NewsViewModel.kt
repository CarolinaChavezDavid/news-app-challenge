package com.example.news_app_challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.data.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
    @Inject
    constructor(private val newsRepository: NewsRepository) : ViewModel() {
        private var _newsState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
        val newsState: StateFlow<NewsUiState> = _newsState

        fun getNews() {
            viewModelScope.launch {
                _newsState.value = NewsUiState.Loading
                _newsState.value =
                    try {
                        NewsUiState.Success(newsRepository.getNewsPlaceHolder())
                    } catch (e: IOException) {
                        NewsUiState.Error
                    } catch (e: HttpException) {
                        NewsUiState.Error
                    }
            }
        }
    }

sealed interface NewsUiState {
    data class Success(val news: List<NewsData>) : NewsUiState

    data object Error : NewsUiState

    data object Loading : NewsUiState
}
