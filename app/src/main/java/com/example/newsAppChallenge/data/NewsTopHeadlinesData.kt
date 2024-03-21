package com.example.newsAppChallenge.data

data class NewsTopHeadlinesData(
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val source: NewsSource,
)

data class NewsSource(
    val name: String,
    val url: String,
)