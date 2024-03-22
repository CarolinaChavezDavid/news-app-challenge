package com.example.newsAppChallenge.domain.model

import com.example.newsAppChallenge.data.database.Entities.NewsEntity
import com.example.newsAppChallenge.data.models.NewsData
import com.example.newsAppChallenge.data.models.UserAddress
import com.example.newsAppChallenge.data.models.UserCompany
import com.example.newsAppChallenge.data.models.UserLocation
import com.example.newsAppChallenge.data.models.UsersData

data class News(
    val id: Int,
    val url: String,
    val title: String,
    val content: String,
    val image: String,
    val category: String,
    val publishedAt: String,
    val userId: Int,
)

fun NewsData.toDomain() =
    News(
        id,
        url,
        title,
        content,
        image,
        category,
        publishedAt,
        userId,
    )

fun NewsEntity.toDomain() =
    News(
        id,
        url,
        title,
        content,
        image,
        category,
        publishedAt,
        userId,
    )


val newsExample =
    News(
        id = 1,
        url = "https://jsonplaceholder.org/posts/lorem-ipsum",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        content = "Ante taciti nulla sit libero orci sed nam. Sagittis suspendisse gravida ornare iaculis cras nullam varius ac ullamcorper. Nunc euismod hendrerit netus ligula aptent potenti. Aliquam volutpat nibh scelerisque at. Ipsum molestie phasellus euismod sagittis mauris, erat ut. Gravida morbi, sagittis blandit quis ipsum mi mus semper dictum amet himenaeos. Accumsan non congue praesent interdum habitasse turpis orci. Ante curabitur porttitor ullamcorper sagittis sem donec, inceptos cubilia venenatis ac. Augue fringilla sodales in ullamcorper enim curae; rutrum hac in sociis! Scelerisque integer varius et euismod aenean nulla. Quam habitasse risus nullam enim. Ultrices etiam viverra mattis aliquam? Consectetur velit vel volutpat eget curae;. Volutpat class mus elementum pulvinar! Nisi tincidunt volutpat consectetur. Primis morbi pulvinar est montes diam himenaeos duis elit est orci. Taciti sociis aptent venenatis dui malesuada dui justo faucibus primis consequat volutpat. Rhoncus ante purus eros nibh, id et hendrerit pellentesque scelerisque vehicula sollicitudin quam. Hac class vitae natoque tortor dolor dui praesent suspendisse. Vehicula euismod tincidunt odio platea aenean habitasse neque ad proin. Bibendum phasellus enim fames risus eget felis et sem fringilla etiam. Integer.",
        image = "https://dummyimage.com/800x430/FFFFFF/lorem-ipsum.png&text=jsonplaceholder.org",
        category = "lorem",
        publishedAt = "04/02/2023 13:25:21",
        userId = 1,
    )
