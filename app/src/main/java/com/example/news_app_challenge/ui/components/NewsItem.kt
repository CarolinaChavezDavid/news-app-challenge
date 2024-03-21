package com.example.news_app_challenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news_app_challenge.R
import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.ui.theme.labelSmallStyle
import com.example.news_app_challenge.ui.theme.titleMediumStyle
import com.example.news_app_challenge.utils.toFormattedDate

@Composable
fun NewsItem(news: NewsData) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model =
                    ImageRequest.Builder(context = LocalContext.current)
                        .data(news.image)
                        .crossfade(true)
                        .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.news_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f),
            )
            Column(
                modifier =
                    Modifier
                        .weight(3f)
                        .padding(12.dp),
            ) {
                Text(text = news.title, style = titleMediumStyle)
                Text(
                    text = news.publishedAt.toFormattedDate(),
                    style = labelSmallStyle,
                    modifier =
                        Modifier.align(
                            Alignment.End,
                        ),
                )
            }
        }
    }
}
