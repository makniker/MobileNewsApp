package com.makniker.itmoapp.presentation

sealed interface UiState {
    class Success(val data: List<NewsArticleUI>): UiState
    class Error(val error: String): UiState
    object Loading: UiState
}