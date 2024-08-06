package com.hasankaraibis.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hasankaraibis.newsapp.data.remote.NewsItemResponse
import com.hasankaraibis.newsapp.data.remote.NewsService
import com.hasankaraibis.newsapp.model.NewsItem
import com.hasankaraibis.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val service = NewsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            var news = produceState<List<NewsItemResponse>>(initialValue = emptyList(), producer = {
//                value = service.getPosts()
//            })

            NewsAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "news_detail_screen") {
                    composable(
                        "news_detail_screen",
                        arguments = listOf(
                            navArgument("newsId") {
                                type = NavType.IntType
                            },
                            navArgument("newsUrl") {
                                type = NavType.IntType
                            }
                        )
                    ) {

                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    if (news.value.isNotEmpty()) {
//                        val items = listOf(
//                            NewsItem(
//                                R.drawable.ic_android_black_24dp,
//                                news.value[0].title,
//                                news.value[0].description
//                            ),
//                            NewsItem(
//                                R.drawable.ic_android_black_24dp,
//                                news.value[1].title,
//                                news.value[1].description
//                            ),
//                            NewsItem(
//                                R.drawable.ic_android_black_24dp,
//                                news.value[3].title,
//                                news.value[3].description
//                            ),
//                            NewsItem(
//                                R.drawable.ic_android_black_24dp,
//                                news.value[4].title,
//                                news.value[4].description
//                            ),
//                            NewsItem(
//                                R.drawable.ic_android_black_24dp,
//                                news.value[5].title,
//                                news.value[5].description
//                            ),
//                        )
//                        ItemList(items = items)
//                    }
                }

            }
        }
    }
}


@Composable
fun ItemList(items: List<NewsItem>) {
    LazyColumn {
        items(items) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
fun ItemCard(item: NewsItem) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = item.description)
    }
}

@Preview
@Composable
fun ItemListPreview() {
    NewsAppTheme {
        val items = listOf(
            NewsItem(R.drawable.ic_android_black_24dp, "Title 1", "Description 1"),
            NewsItem(R.drawable.ic_android_black_24dp, "Title 2", "Description 2"),
            NewsItem(R.drawable.ic_android_black_24dp, "Title 3", "Description 3"),
            NewsItem(R.drawable.ic_android_black_24dp, "Title 4", "Description 3"),
            NewsItem(R.drawable.ic_android_black_24dp, "Title 5", "Description 3"),
        )
        ItemList(items = items)
    }
}