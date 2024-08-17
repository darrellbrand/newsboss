package com.djf.newsboss.data

import com.djf.newsboss.util.APILatestResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

/*
    @GET("latest?country=us&apikey=pub_51074673c2726bf87e6698bf907871a0f54d2")
    suspend fun getLatest(): Response<APILatestResult>

    @GET("latest?country=us&apikey=pub_51074673c2726bf87e6698bf907871a0f54d2&q=cryptocurrency")
    suspend fun getCryptoLatest(): Response<APILatestResult>*/

    @GET("everything?apiKey=69f30318fca84d1896fd5f3097064128&q=bitcoin")
    suspend fun getLatest(): Response<APILatestResult>

    @GET("everything?apiKey=69f30318fca84d1896fd5f3097064128")
    suspend fun getCryptoLatest(): Response<APILatestResult>


}