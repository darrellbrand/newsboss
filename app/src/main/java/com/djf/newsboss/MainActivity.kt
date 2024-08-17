package com.djf.newsboss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djf.newsboss.presentation.NewsScreen
import com.djf.newsboss.presentation.NewsViewModel
import com.djf.newsboss.ui.theme.NewsBossTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : NewsViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            val news = viewModel.news.collectAsStateWithLifecycle()
            NewsBossTheme {
                NewsScreen(list = news)
            }
        }
    }

}