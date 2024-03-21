package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.data.userExample
import com.example.newsAppChallenge.ui.components.AppHeader
import com.example.newsAppChallenge.ui.components.UserItem

@Composable
fun UsersScreen(usersList: List<UsersData>) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader()
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(usersList) { user ->
                UserItem(usersData = user)
            }
        }
    }
}

@Preview
@Composable
fun UserPreview() {
    UsersScreen(List(10) { userExample })
}
