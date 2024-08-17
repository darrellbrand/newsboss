package com.djf.newsboss.data

import com.djf.newsboss.util.APILatestResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {


    @GET("latest?country=us&apikey=pub_51074673c2726bf87e6698bf907871a0f54d2")
    suspend fun getLatest(): Response<APILatestResult>

    @GET("latest?country=us&apikey=pub_51074673c2726bf87e6698bf907871a0f54d2&q=cryptocurrency")
    suspend fun getCryptoLatest(): Response<APILatestResult>


}