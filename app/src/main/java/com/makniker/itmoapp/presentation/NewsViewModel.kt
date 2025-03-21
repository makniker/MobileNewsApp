package com.makniker.itmoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makniker.itmoapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchNews() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.value = UiState.Loading
        repository.fetchNews("bitcoin").fold(
            onSuccess = {
                _uiState.value = UiState.Success(it)
            },
            onFailure = {
                _uiState.value = UiState.Error(it.message ?: "Something gone wrong")
            }
        )
    }
}