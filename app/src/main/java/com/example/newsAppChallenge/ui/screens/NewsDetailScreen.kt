package com.example.newsAppChallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.NewsDetailData
import com.example.newsAppChallenge.data.NewsExample
import com.example.newsAppChallenge.data.UsersData
import com.example.newsAppChallenge.data.userExample
import com.example.newsAppChallenge.ui.components.CategoryItem
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.newsAppChallenge.ui.theme.titleLargeStyle
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import com.example.newsAppChallenge.utils.toFormattedDate
import com.example.news_app_challenge.R

@Composable
fun NewsDetailScreen(
    newsDetail: NewsDetailData,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                NewsDetailHeader(news = newsDetail.newsData)
            }
            item {
                DetailsNewsContentHeader(newsDetail)
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
            AsyncImage(
                model = "https://picsum.photos/400",
                contentDescription = "news image",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .blur(
                            radiusX = 30.dp,
                            radiusY = 30.dp,
                        )
                        .drawWithCache {
                            val gradient =
                                Brush.verticalGradient(
                                    colors = listOf(Color.Gray, Color.Black),
                                    startY = size.height.div(3),
                                    endY = size.height,
                                )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        },
                contentScale = ContentScale.Crop,
            )
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
                CategoryItem(category = "general", alpha = 0.3f, padding = 8)
            }
        }
    }
}

@Composable
fun DetailsNewsContentHeader(
    newsDetail: NewsDetailData,
) {
    val userPhotos = "https://picsum.photos/400"

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = newsDetail.newsData.publishedAt.toFormattedDate(),
            style = titleMediumStyle,
            modifier =
                Modifier.align(
                    Alignment.End,
                ),
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Column {
                AsyncImage(
                    model = "https://picsum.photos/400",
                    contentDescription = stringResource(id = R.string.user_image),
                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = "PUBLISHED BY ${newsDetail.userData.firstname} ${newsDetail.userData.lastname}",
                    style = labelSmallStyle,
                )
            }

            Column() {
                val images = List(4) { userPhotos}
                LazyRow {
                    item {
                        OverlappingRow {
                            images.forEach { user ->
                                AsyncImage(
                                    model = "https://picsum.photos/400",
                                    contentDescription = stringResource(id = R.string.user_image),
                                    modifier =
                                        Modifier
                                            .size(50.dp)
                                            .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(id = R.string.read_by_label),
                    style = labelSmallStyle,
                )
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



@Preview
@Composable
fun PreviewNewsDetailScreen() {
}
