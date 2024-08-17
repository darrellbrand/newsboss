package com.djf.newsboss.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djf.newsboss.domain.GetNewsUseCase
import com.djf.newsboss.domain.NewsRepository
import com.djf.newsboss.util.APILatestResult
import com.djf.newsboss.util.APILatestResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _news = MutableStateFlow(listOf(APILatestResultItem()))
    val news = _news

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            getNewsUseCase.getNews()?.let {
                _news.value = it
            }
        }
    }
    fun fetchCryptoNews() {
        viewModelScope.launch {
            getNewsUseCase.getCryptoNews()?.let {
                _news.value = it
            }
        }
    }


}