package com.makniker.itmoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makniker.itmoapp.presentation.NewsListScreen
import com.makniker.itmoapp.ui.theme.ItmoappTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItmoappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsListScreen(viewModel = viewModel(), modifier = Modifier.fillMaxSize().padding(innerPadding))
                }
            }
        }
    }
}

