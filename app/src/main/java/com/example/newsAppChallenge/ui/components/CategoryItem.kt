package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.ui.theme.RussianViolet
import com.example.newsAppChallenge.ui.theme.titleMediumStyle

@Composable
fun CategoryItem(
    category: String,
    alpha: Float = 1f,
    padding: Int = 16,
    color: Color = RussianViolet,
) {
    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = color.copy(alpha = alpha),
            ),
    ) {
        Text(
            modifier = Modifier.padding(padding.dp),
            text = category,
            style = titleMediumStyle.copy(color = MaterialTheme.colorScheme.onPrimary),
        )
    }
}

@Composable
@Preview
fun PreviewCategoryItem() {
    CategoryItem(category = "general")
}
