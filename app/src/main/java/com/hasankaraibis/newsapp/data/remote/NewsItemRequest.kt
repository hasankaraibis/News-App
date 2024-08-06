package com.hasankaraibis.newsapp.data.remote


data class NewsItemRequest(
    val from: String,
    val to: String,
    val sortBy: String
)