package com.example.newsAppChallenge.domain.usecases

import com.example.newsAppChallenge.data.database.Entities.newsEntityExample
import com.example.newsAppChallenge.domain.model.newsExample
import com.example.newsAppChallenge.domain.repositories.NewsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class GetNewsUseCasesTest {
    private val newsRepository: NewsRepository = Mockito.mock(NewsRepository::class.java)
    private lateinit var useCase: GetNewsUseCases

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        useCase = GetNewsUseCases(newsRepository)
    }

    @Test
    fun `when getNewsPlaceHolderApi is not Empty then usecase returns the list()`() =
        runBlocking {
            Mockito.`when`(newsRepository.getNewsPlaceHolderApi()).thenReturn(listOf(newsExample))
            assertEquals(listOf(newsExample), useCase())
            verify(newsRepository).insertNews(listOf(newsEntityExample))
            verify(newsRepository).clearNews()
        }
}
