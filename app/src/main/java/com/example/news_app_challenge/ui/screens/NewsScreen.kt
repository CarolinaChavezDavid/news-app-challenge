package com.example.news_app_challenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_app_challenge.R
import com.example.news_app_challenge.data.NewsData
import com.example.news_app_challenge.data.NewsSource
import com.example.news_app_challenge.data.NewsTopHeadlinesData
import com.example.news_app_challenge.ui.viewmodel.NewsUiState
import com.example.news_app_challenge.ui.components.CategoryNewsList
import com.example.news_app_challenge.ui.components.NewsItem

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    newsUiState: NewsUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (newsUiState) {
        is NewsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is NewsUiState.Success -> NewsListSection(news = newsUiState.news)
        is NewsUiState.Error -> ErrorScreen({ }, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun NewsListSection(news: List<NewsData>) {
    Column {
        CategoryNewsList(itemSelected = "general")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(news) { item ->
                NewsItem(news = item)
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "",
        )
        Text(text = stringResource(R.string.failed_connection), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

val NewsExample =
    NewsTopHeadlinesData(
        title = "NAB 2024: Ateme Propels Spatial Computing with TITAN Encoders for Apple Vision Pro",
        description = "Ateme announces that its TITAN encoders now enable new ways of consuming video assets on the Apple Vision Pro. Leveraging Ateme’s Gen 7 compression ...",
        content = "NAB 2024: Ateme Propels Spatial Computing with TITAN Encoders for Apple Vision Pro\nAteme announces that its TITAN encoders now enable new ways of consuming video assets on the Apple Vision Pro. Leveraging Ateme’s Gen 7 compression engine to support S... [1422 chars]",
        url = "https://www.sportsvideo.org/2024/03/19/nab-2024-ateme-propels-spatial-computing-with-titan-encoders-for-apple-vision-pro/",
        image = "https://www.sportsvideo.org/wp-content/uploads/2015/06/Ateme-web.png",
        publishedAt = "2024-03-19T18:54:00Z",
        source =
            NewsSource(
                name = "Sports Video Group",
                url = "https://www.sportsvideo.org",
            ),
    )

@Composable
@Preview
fun NewsScreenPreview() {
}
