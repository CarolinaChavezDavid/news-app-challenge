package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.NewsDetailDataModel
import com.example.newsAppChallenge.data.models.userExample
import com.example.newsAppChallenge.ui.components.CategoryItem
import com.example.newsAppChallenge.ui.components.GradientImageComponent
import com.example.newsAppChallenge.ui.components.UsersImageComponent
import com.example.newsAppChallenge.ui.theme.PurpleGrey80
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.newsAppChallenge.ui.theme.titleLargeStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.newsAppChallenge.ui.viewmodel.NewsUiState
import com.example.newsAppChallenge.ui.viewmodel.NewsViewModel
import com.example.newsAppChallenge.utils.toFormattedDate
import com.example.news_app_challenge.R

@Composable
fun NewsDetailScreen(
    newsId: String,
    newsViewModel: NewsViewModel = hiltViewModel(),
    onUserClicked: (userId: String) -> Unit,
    onUsersListClicked: () -> Unit,
) {
    newsViewModel.getNewsDetail(newsId)
    val uiState by newsViewModel.uiState.collectAsState()
    Scaffold {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (uiState) {
                is NewsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is NewsUiState.Success ->
                    NewsDetailContent(
                        newsData = newsViewModel.newsDetail.collectAsState().value,
                        it,
                        onUserClicked,
                        onUsersListClicked,
                    )
                is NewsUiState.Error -> ErrorScreen({ }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun NewsDetailContent(
    newsData: NewsData,
    paddingValues: PaddingValues,
    onUserClicked: (userId: String) -> Unit,
    onUsersListClicked: () -> Unit,
) {
    val newsDetail = NewsDetailDataModel(newsData, userExample)
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                NewsDetailHeader(news = newsDetail.newsData)
            }
            item {
                DetailsNewsContentHeader(newsDetail, onUserClicked, onUsersListClicked)
            }
            item {
                Text(
                    text = newsDetail.newsData.content,
                    style = titleMediumStyle,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun NewsDetailHeader(news: NewsData) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(300.dp),
        shape =
            RoundedCornerShape(
                bottomStart = 40.dp,
                bottomEnd = 40.dp,
            ),
    ) {
        Box {
            GradientImageComponent(imageUrl = news.image)
            Column(
                modifier =
                    Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart),
            ) {
                Text(
                    text = news.title,
                    style = titleLargeStyle.copy(color = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
                )
                CategoryItem(category = "general", alpha = 0.3f, padding = 8, color = PurpleGrey80)
            }
        }
    }
}

@Composable
fun DetailsNewsContentHeader(
    newsDetail: NewsDetailDataModel,
    onUserClicked: (userId: String) -> Unit,
    onUsersListClicked: () -> Unit,
) {
    val userPhotos = "https://picsum.photos/400"

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = newsDetail.newsData.publishedAt.toFormattedDate(),
            style = titleMediumStyle,
            modifier =
                Modifier.align(
                    Alignment.End,
                ),
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
            Column(modifier = Modifier.clickable { onUserClicked(newsDetail.userData.id.toString()) }) {
                UsersImageComponent(imageSize = 50.dp, userId = newsDetail.userData.id.toString())
                Text(
                    text = "PUBLISHED BY ${newsDetail.userData.firstname} ${newsDetail.userData.lastname}",
                    style = labelSmallStyle,
                )
            }

            Column(modifier = Modifier.clickable { onUsersListClicked() }) {
                val images = List(4) { userPhotos }
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(id = R.string.read_by_label),
                    style = labelSmallStyle,
                )
                LazyRow {
                    item {
                        OverlappingRow {
                            images.forEach { user ->
                                UsersImageComponent(imageSize = 50.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val factor = (1 - 0.40f)

    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val widthsExceptFirst = placeables.subList(1, placeables.size).sumOf { it.width }
            val firstWidth = placeables.getOrNull(0)?.width ?: 0
            val width = (widthsExceptFirst * factor + firstWidth).toInt()
            val height = placeables.maxOf { it.height }
            layout(width, height) {
                var x = 0
                for (placeable in placeables) {
                    placeable.placeRelative(x, 0, 0f)
                    x += (placeable.width * factor).toInt()
                }
            }
        },
    )
}
