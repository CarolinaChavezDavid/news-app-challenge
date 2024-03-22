package com.example.newsAppChallenge.ui.viewmodel

import com.example.newsAppChallenge.data.models.userExample
import com.example.newsAppChallenge.domain.repositories.UsersRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UsersViewModelTest {
    private val usersRepository: UsersRepository = mock(UsersRepository::class.java)
    private lateinit var usersViewModel: UsersViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        usersViewModel = UsersViewModel(usersRepository)
    }

    @Test
    fun `when getUsersList call is right then uiState is success`() =
        runBlocking {
            Mockito.`when`(usersRepository.getUsersList()).thenReturn(listOf(userExample))
            usersViewModel.getUsersList()
            assertEquals(UsersUiState.Success, usersViewModel.uiState.value)
        }

    @Test
    fun `when getUsersList call fails then uiState is error`() =
        runBlocking {
            Mockito.`when`(usersRepository.getUsersList()).thenThrow(NullPointerException())
            usersViewModel.getUsersList()
            assertEquals(UsersUiState.Error, usersViewModel.uiState.value)
        }

    @Test
    fun `when getUsersDetail call is right then uiState is success`() =
        runBlocking {
            Mockito.`when`(usersRepository.getUsersList()).thenReturn(listOf(userExample))
            usersViewModel.getUsersList()
            assertEquals(UsersUiState.Success, usersViewModel.uiState.value)
        }

    @Test
    fun `when getUsersDetail call fails then uiState is error`() =
        runBlocking {
            Mockito.`when`(usersRepository.getUsersList()).thenThrow(NullPointerException())
            usersViewModel.getUsersList()
            assertEquals(UsersUiState.Error, usersViewModel.uiState.value)
        }
}
