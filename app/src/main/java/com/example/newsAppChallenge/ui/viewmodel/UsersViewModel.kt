package com.example.newsAppChallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsAppChallenge.data.models.UsersData
import com.example.newsAppChallenge.data.models.userExample
import com.example.newsAppChallenge.domain.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
    @Inject
    constructor(private val usersRepository: UsersRepository) :
    ViewModel() {
        private var _uiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
        val uiState: StateFlow<UsersUiState> = _uiState

        private var _usersList = MutableStateFlow<List<UsersData>>(emptyList())
        val usersList: StateFlow<List<UsersData>> = _usersList

        private var _userData = MutableStateFlow<UsersData>(userExample)
        val usersData: StateFlow<UsersData> = _userData

        fun getUsersList() {
            viewModelScope.launch {
                _uiState.value = UsersUiState.Loading

                try {
                    _uiState.value = UsersUiState.Success
                    _usersList.value = usersRepository.getUsersList()
                } catch (e: RuntimeException) {
                    _uiState.value = UsersUiState.Error
                }
            }
        }

        fun getUserById(usersId: String) {
            viewModelScope.launch {
                _uiState.value = UsersUiState.Loading

                try {
                    _uiState.value = UsersUiState.Success
                    _userData.value = usersRepository.getUserDetail(usersId)
                } catch (e: RuntimeException) {
                    _uiState.value = UsersUiState.Error
                }
            }
        }
    }

sealed interface UsersUiState {
    data object Success : UsersUiState

    data object Error : UsersUiState

    data object Loading : UsersUiState
}
