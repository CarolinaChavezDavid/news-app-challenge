package com.example.newsAppChallenge.domain.repositories

import com.example.newsAppChallenge.data.database.Entities.newsEntityExample
import com.example.newsAppChallenge.data.database.dao.NewsDao
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.newsDataExample
import com.example.newsAppChallenge.data.services.NewsApiServices
import com.example.newsAppChallenge.domain.model.newsExample
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class NewsRepositoryImplTest {
    private val newsServices: NewsApiServices = Mockito.mock(NewsApiServices::class.java)
    private val newsDao: NewsDao = Mockito.mock(NewsDao::class.java)

    private val newsRepository = NewsRepositoryImpl(newsServices, newsDao)

    @Test
    fun `when getNewsPlaceHolderApi is executed then returns a List of News`() =
        runBlocking {
            val newsList = List(5) { newsExample }
            val newsDataList = List(5) { newsDataExample }
            Mockito.`when`(newsServices.getNewsPlaceHolder()).thenReturn(newsDataList)
            TestCase.assertEquals(newsList, newsRepository.getNewsPlaceHolderApi())
        }

    @Test
    fun `when getNewsFromDatabase is executed then returns a List of News `() =
        runBlocking {
            val newsList = List(5) { newsExample }
            val newsEntityList = List(5) { newsEntityExample }
            Mockito.`when`(newsDao.getAllNews()).thenReturn(newsEntityList)
            TestCase.assertEquals(newsList, newsRepository.getNewsFromDatabase())
        }

    @Test
    fun `when getNewsDetailPlaceHolder is executed then returns NewsData `() =
        runBlocking {
            Mockito.`when`(newsServices.getNewsDetailPlaceHolder("1")).thenReturn(newsDataExample)
            TestCase.assertEquals(newsDataExample, newsRepository.getNewsDetailPlaceHolder("1"))
        }
}
