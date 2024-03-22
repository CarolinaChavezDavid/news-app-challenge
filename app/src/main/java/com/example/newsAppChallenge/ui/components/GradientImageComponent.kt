package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsAppChallenge.ui.theme.Onyx
import com.example.news_app_challenge.R

@Composable
fun GradientImageComponent(imageUrl: String, cornerRadius: Dp = 0.dp) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.news_image),
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
                            colors = listOf(Color.Gray, Onyx),
                            startY = size.height.div(3),
                            endY = size.height,
                        )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }.clip(RoundedCornerShape(cornerRadius)),
        contentScale = ContentScale.Crop,
    )
}
