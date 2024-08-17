package com.djf.newsboss.domain

import com.djf.newsboss.util.APILatestResultItem

interface NewsRepository {

    suspend fun getNews() : List<APILatestResultItem>?
}