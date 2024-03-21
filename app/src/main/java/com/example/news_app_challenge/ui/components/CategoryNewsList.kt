package com.example.news_app_challenge.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.news_app_challenge.data.newsCategories

@Composable
fun CategoryNewsList(itemSelected: String) {
    LazyRow {
        items(newsCategories) { item ->
            CategoryItem(category = item)
        }
    }
}

@Composable
fun CategoryItem(category: String) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = category)
    }
}

@Preview
@Composable
fun CategoryListPreview()  {
    CategoryNewsList(itemSelected = "general")
}
