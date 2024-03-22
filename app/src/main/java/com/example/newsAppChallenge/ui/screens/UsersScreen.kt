package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsAppChallenge.data.models.UsersData
import com.example.newsAppChallenge.ui.components.AppHeader
import com.example.newsAppChallenge.ui.components.UserItem
import com.example.newsAppChallenge.ui.viewmodel.UsersUiState
import com.example.newsAppChallenge.ui.viewmodel.UsersViewModel

@Composable
fun UsersScreen(
    usersViewModel: UsersViewModel = hiltViewModel(),
    onUserClicked: (userId: String) -> Unit,
) {
    usersViewModel.getUsersList()
    val uiState by usersViewModel.uiState.collectAsState()

    Scaffold {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (uiState) {
                is UsersUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is UsersUiState.Success ->
                    UsersScreenContent(
                        usersList = usersViewModel.usersList.collectAsState().value,
                        paddingValues = it,
                        onUserClicked = onUserClicked,
                    )
                is UsersUiState.Error -> ErrorScreen({ }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun UsersScreenContent(
    usersList: List<UsersData>,
    paddingValues: PaddingValues,
    onUserClicked: (userId: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader()
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(usersList) { user ->
                UserItem(usersData = user, onUserClicked = onUserClicked)
            }
        }
    }
}
