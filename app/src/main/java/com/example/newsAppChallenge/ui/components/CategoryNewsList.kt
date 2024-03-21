package com.example.newsAppChallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.newsAppChallenge.data.newsCategories

@Composable
fun CategoryNewsList(itemSelected: String) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(newsCategories) { item ->
            CategoryItem(category = item)
        }
    }
}
