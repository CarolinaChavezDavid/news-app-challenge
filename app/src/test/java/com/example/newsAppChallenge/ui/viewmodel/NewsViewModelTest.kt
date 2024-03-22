package com.example.newsAppChallenge.ui.viewmodel

import com.example.newsAppChallenge.data.models.newsDataExample
import com.example.newsAppChallenge.domain.model.newsExample
import com.example.newsAppChallenge.domain.repositories.NewsRepository
import com.example.newsAppChallenge.domain.usecases.GetNewsUseCases
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NewsViewModelTest {
    private val newsRepository: NewsRepository = Mockito.mock(NewsRepository::class.java)
    private val useCase = GetNewsUseCases(newsRepository)
    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        newsViewModel = NewsViewModel(newsRepository, useCase)
    }

    @Test
    fun `when getNews call is right then uiState is success`() =
        runBlocking {
            Mockito.`when`(newsRepository.getNewsPlaceHolderApi()).thenReturn(listOf(newsExample))
            Mockito.`when`(useCase()).thenReturn(listOf(newsExample))
            newsViewModel.getNews()
            assertEquals(NewsUiState.Success, newsViewModel.uiState.value)
        }

    @Test
    fun `when getNews call fails then uiState is error`() =
        runBlocking {
            Mockito.`when`(newsRepository.getNewsPlaceHolderApi()).thenReturn(listOf())
            Mockito.`when`(useCase()).thenReturn(listOf())
            newsViewModel.getNews()
            assertEquals(NewsUiState.Error, newsViewModel.uiState.value)
        }

    @Test
    fun `when getNewsDetail call is right then uiState is success`() =
        runBlocking {
            Mockito.`when`(newsRepository.getNewsDetailPlaceHolder("1")).thenReturn(newsDataExample)
            newsViewModel.getNewsDetail("1")
            assertEquals(NewsUiState.Success, newsViewModel.uiState.value)
        }

    @Test
    fun `when getNewsDetail call fails then uiState is error`() =
        runBlocking {
            Mockito.`when`(newsRepository.getNewsDetailPlaceHolder("1")).thenThrow(NullPointerException())
            newsViewModel.getNewsDetail("1")
            assertEquals(NewsUiState.Error, newsViewModel.uiState.value)
        }

    @Test
    fun `when searchNews filter is executed then right amount of news are shown`() =
        runBlocking {
            val newsList = List(5) { newsExample }
            val query = "ipsum "
            Mockito.`when`(newsRepository.getNewsPlaceHolderApi()).thenReturn(newsList)
            Mockito.`when`(useCase()).thenReturn(newsList)
            newsViewModel.getNews()
            newsViewModel.searchNews(query)
            assertEquals(newsList.size, newsViewModel.newsList.value.size)
        }
}
