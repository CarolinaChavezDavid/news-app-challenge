package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.ui.components.AppHeader
import com.example.newsAppChallenge.ui.components.CategoryNewsList
import com.example.newsAppChallenge.ui.components.NewsItem
import com.example.newsAppChallenge.ui.components.NewsPagerComponent
import com.example.newsAppChallenge.ui.components.SearchComponent
import com.example.news_app_challenge.R

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    news: List<NewsData>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClickItem: (newsId: String) -> Unit
) {
    Column {
        AppHeader()
        LazyColumn(modifier = modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                SearchComponent()
            }
            item { NewsPagerComponent(news) }
            item {
                CategoryNewsList(itemSelected = "general")
            }
            items(news) { item ->
                NewsItem(news = item, onClickItem)
            }
        }
    }
}

@Composable
@Preview
fun NewsScreenPreview() {
}
