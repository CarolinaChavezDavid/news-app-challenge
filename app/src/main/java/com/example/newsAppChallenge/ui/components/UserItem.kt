package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.data.userExample
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle

@Composable
fun UserItem(usersData: UsersData) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
        ) {
            UsersImageComponent(imageSize = 50.dp)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "${usersData.firstname} ${usersData.firstname}",
                    style = titleMediumStyle,
                )
                Text(
                    text = "${usersData.address.city} | ${usersData.company.name}",
                    style = labelSmallStyle,
                    maxLines = 2,
                )
            }
        }
    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(userExample)
}
