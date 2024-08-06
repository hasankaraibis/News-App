package com.hasankaraibis.newsapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class NewsServiceImpl(
    private val client: HttpClient
) : NewsService {
    override suspend fun getPosts(): List<NewsItemResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            return emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            return emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            return emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun createPost(postRequest: NewsItemRequest): NewsItemResponse? {
        return try {
            client.post<NewsItemResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}

//val client = HttpClient {
//    install(ContentNegotiation) {
//        json(Json {
//            ignoreUnknownKeys = true // Handle unknown fields gracefully
//        })
//    }
//}