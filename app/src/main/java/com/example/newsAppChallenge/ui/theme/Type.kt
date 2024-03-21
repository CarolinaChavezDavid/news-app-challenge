package com.example.newsAppChallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

val defaultTextStyle =
    TextStyle(
        fontFamily = montserratFamily,
        platformStyle =
            PlatformTextStyle(
                includeFontPadding = false,
            ),
        lineHeightStyle =
            LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
    )

val titleMediumStyle =
    defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    )

val titleLargeStyle =
    defaultTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    )

val labelSmallStyle =
    defaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    )

val NewsAppTypography =
    Typography(
        titleMedium =
            defaultTextStyle.copy(
                fontSize = 50.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                fontWeight = FontWeight.W500,
            ),
    )
