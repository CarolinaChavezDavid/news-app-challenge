package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.data.NewsExample
import com.example.newsAppChallenge.ui.theme.labelSmallStyle
import com.example.news_app_challenge.R
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPagerComponent(news: List<NewsData>) {
    val pagerState =
        rememberPagerState(pageCount = {
            4
        })
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(200.dp),
        pageSpacing = 12.dp
    ) { page ->
        Card(
            Modifier
                .size(200.dp)
                .graphicsLayer {
                    val pageOffset =
                        (
                            (pagerState.currentPage - page) +
                                pagerState
                                    .currentPageOffsetFraction
                        ).absoluteValue
                    alpha =
                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f),
                        )
                },
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model =
                        ImageRequest.Builder(context = LocalContext.current).data(news[page].image)
                            .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.news_image),
                    contentScale = ContentScale.FillBounds,
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(12.dp)),
                )
                Text(
                    text = news[page].title,
                    style = labelSmallStyle,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPager() {
    NewsPagerComponent(listOf(NewsExample, NewsExample, NewsExample, NewsExample, NewsExample, NewsExample))
}
