package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.newsAppChallenge.data.NewsData
import com.example.newsAppChallenge.ui.theme.GhostWhite
import com.example.newsAppChallenge.ui.theme.titleMediumStyle
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPagerComponent(
    news: List<NewsData>,
    onClickItem: (newsId: String) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { news.size })
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(200.dp),
        pageSpacing = 12.dp,
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
                }.clickable { onClickItem(news[page].id.toString()) },
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                GradientImageComponent(imageUrl = news[page].image, cornerRadius = 12.dp)
                Text(
                    text = news[page].title,
                    style = titleMediumStyle.copy(GhostWhite),
                    modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
                )
            }
        }
    }
}
