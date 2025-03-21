package com.makniker.itmoapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makniker.itmoapp.R
import com.makniker.itmoapp.ui.theme.ItmoappTheme

@Composable
fun NewsListScreen(viewModel: NewsViewModel, modifier: Modifier) {
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }
    val state = viewModel.uiState.collectAsState()
    when (val fetchedState = state.value) {
        is UiState.Error -> ErrorScreen(fetchedState.error, modifier) { viewModel.fetchNews() }
        UiState.Loading -> LoadingScreen(modifier)
        is UiState.Success -> ContentScreen(fetchedState.data, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(data: List<NewsArticleUI>, modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var item by remember { mutableStateOf(NewsArticleUI("", "", "", "")) }
    val sheetState = rememberModalBottomSheetState()
    rememberCoroutineScope()
    Column(
        modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current news about bitcoin")
        LazyColumn {
            items(data) {
                CardItem(it, Modifier) {
                    item = it
                    showBottomSheet = true
                }
                Spacer(Modifier.size(16.dp))
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                }, sheetState = sheetState
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = item.urlToImage,
                        contentDescription = "Image for this news",
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        error = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(text = item.title, fontWeight = FontWeight.Bold)
                    Text(text = "Author: ${item.author}", fontWeight = FontWeight.Bold)
                    Text(text = item.description)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
    }
}

@Composable()
fun ErrorScreen(error: String, modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = error)
        Button(onClick = onRetry) {
            Text("Try Again")
        }
    }
}

@Composable
fun CardItem(item: NewsArticleUI, modifier: Modifier = Modifier, onCardClick: () -> Unit = {}) {
    OutlinedCard(onClick = onCardClick) {
        Column(
            modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (item.urlToImage != "") {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = item.urlToImage,
                    contentDescription = "Image for this news",
                    error = painterResource(R.drawable.ic_launcher_foreground),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(text = item.title, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(16.dp))
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun LoadingPreview() {
    ItmoappTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun ErrorPreview() {
    ItmoappTheme {
        ErrorScreen("Something gone wrong") {}
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun ContentPreview() {
    ItmoappTheme {
        ContentScreen(
            listOf(
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
                NewsArticleUI(
                    author = "Ilon Musk",
                    title = "I do something cringe",
                    urlToImage = "",
                    description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
                ),
            )
        )
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun CardPreview() {
    ItmoappTheme {
        CardItem(
            item = NewsArticleUI(
                author = "Ilon Musk",
                title = "I do something cringe",
                urlToImage = "",
                description = "Very long string very long string Very long string very long string Very long string very long string Very long string very long string"
            )
        )
    }
}