package com.example.newsAppChallenge.domain.repositories

import com.example.newsAppChallenge.data.models.userExample
import com.example.newsAppChallenge.data.services.UserApiServices
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UsersRepositoryImplTest {
    private val userService: UserApiServices = mock(UserApiServices::class.java)
    private val usersRepository = UsersRepositoryImpl(userService)

    @Test
    fun `when getUsersList  is executed then returns a List of UsersData`() =
        runBlocking {
            val usersList = List(5) { userExample }
            Mockito.`when`(userService.getUsersPlaceHolder()).thenReturn(usersList)
            assertEquals(usersList, usersRepository.getUsersList())
        }

    @Test
    fun `when getUserDetail is executed then returns a UsersData `() =
        runBlocking {
            val usersData = userExample
            Mockito.`when`(userService.getUsersDetailPlaceHolder("1")).thenReturn(usersData)
            assertEquals(usersData, usersRepository.getUserDetail("1"))
        }
}
