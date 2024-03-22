package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.ui.components.AppHeader
import com.example.newsAppChallenge.ui.components.CategoryNewsList
import com.example.newsAppChallenge.ui.components.NewsItem
import com.example.newsAppChallenge.ui.components.NewsPagerComponent
import com.example.newsAppChallenge.ui.components.SearchComponent
import com.example.newsAppChallenge.ui.theme.titleLargeStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.newsAppChallenge.utils.getGreeting
import com.example.news_app_challenge.R

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    news: List<NewsData>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClickItem: (newsId: String) -> Unit,
) {
    Column {
        AppHeader()
        LazyColumn(modifier = modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Spacer(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(20.dp),
                )
            }
            item {
                SearchComponent()
            }
            item {
                GreetingSection()
            }
            item { NewsPagerComponent(news, onClickItem) }
            item {
                CategoryNewsList()
            }
            items(news) { item ->
                NewsItem(news = item, onClickItem)
            }
        }
    }
}

@Composable
fun GreetingSection() {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Text(
            text = getGreeting(),
            style = titleLargeStyle,
        )

        Text(
            text = stringResource(id = R.string.app_greetings),
            style = titleMediumStyle,
        )
    }
}
