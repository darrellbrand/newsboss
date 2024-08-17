package com.djf.newsboss.data

import com.djf.newsboss.util.APILatestResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {


    @GET("latest?apikey=pub_51074673c2726bf87e6698bf907871a0f54d2")
    suspend fun getLatest(): Response<APILatestResult>
}