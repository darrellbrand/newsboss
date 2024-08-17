package com.djf.newsboss.domain

import com.djf.newsboss.util.APILatestResultItem
import javax.inject.Inject


class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNews() : List<APILatestResultItem>?{
        return newsRepository.getNews()
    }
    suspend fun getCryptoNews() : List<APILatestResultItem>?{
        return newsRepository.getCryptoNews()
    }
}