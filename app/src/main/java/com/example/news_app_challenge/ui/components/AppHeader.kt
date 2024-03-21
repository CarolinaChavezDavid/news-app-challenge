package com.example.news_app_challenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_app_challenge.R

@Composable
fun AppHeader() {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ic_news_app_logo),
            contentDescription =
                stringResource(
                    id = R.string.app_logo,
                ),
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    AppHeader()
}
