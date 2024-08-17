package com.djf.newsboss.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djf.newsboss.domain.GetNewsUseCase
import com.djf.newsboss.domain.NewsRepository
import com.djf.newsboss.util.APILatestResult
import com.djf.newsboss.util.APILatestResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _news = MutableStateFlow(listOf(APILatestResultItem()))
    val news = _news.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.NewsScreen().screen)
    val screen = _screenState

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {

                getNewsUseCase.getNews()?.let { news ->
                    println(news.size)
                    _news.value = news.filter { it.image_url.isNotBlank()  }
                }
                _screenState.value = ScreenState.NewsScreen().screen
        }
    }

    fun fetchCryptoNews() {
        viewModelScope.launch {
            getNewsUseCase.getCryptoNews()?.let { news ->
                _news.value = news.filter { it.image_url.isNotEmpty() }
            }
            _screenState.value = ScreenState.CryptoNewsScreen().screen
        }
    }


}