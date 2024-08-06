package com.hasankaraibis.newsapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface NewsService {
    suspend fun getPosts(): List<NewsItemResponse>

    suspend fun createPost(postRequest: NewsItemRequest): NewsItemResponse?

    companion object {
        fun create(): NewsService {
            return NewsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }

                }
            )
        }
    }
}