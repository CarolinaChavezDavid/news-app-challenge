package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.news_app_challenge.R

@Composable
fun UserItem(
    usersData: UsersData,
    onUserClicked: (userId: String) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable { onUserClicked(usersData.id.toString()) },
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
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(modifier = Modifier.fillMaxHeight()) {
                UsersImageComponent(imageSize = 50.dp, userId = usersData.id.toString())
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "${usersData.firstname} ${usersData.lastname}",
                        style = titleMediumStyle,
                    )
                    Text(
                        text = "${usersData.address.city} | ${usersData.company.name}",
                        style = labelSmallStyle,
                        maxLines = 2,
                    )
                }
            }

            CategoryItem(category = stringResource(id = R.string.follow), padding = 8)
        }
    }
}
