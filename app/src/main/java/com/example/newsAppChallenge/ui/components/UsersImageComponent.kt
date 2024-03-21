package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news_app_challenge.R

@Composable
fun UsersImageComponent(imageSize: Dp) {
    AsyncImage(
        model =
            ImageRequest.Builder(context = LocalContext.current)
                .data("https://picsum.photos/200")
                .crossfade(true)
                .build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.user_image),
        contentScale = ContentScale.Crop,
        modifier =
            Modifier
                .size(imageSize)
                .clip(CircleShape),
    )
}
