package com.djf.newsboss.data.repository

import com.djf.newsboss.data.APIService
import com.djf.newsboss.domain.NewsRepository
import com.djf.newsboss.util.APILatestResult
import com.djf.newsboss.util.APILatestResultItem
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: APIService) : NewsRepository {
    override suspend fun getNews(): List<APILatestResultItem>? {
        try {
            val response = api.getLatest()
            if (response.isSuccessful) {
                return response.body()?.articles
            } else {
                println("news repo news response failed")
            }
        } catch (e: Exception) {
            println("news repo exception $e")
        }

        return null

    }

    override suspend fun getCryptoNews(): List<APILatestResultItem>? {
        try {
            val response = api.getCryptoLatest()
            if (response.isSuccessful) {
                return response.body()?.articles
            } else {
                println("news repo crypto response failed")
            }
        } catch (e: Exception) {
            println("news repo crypto exception $e")
        }

        return null
    }

}