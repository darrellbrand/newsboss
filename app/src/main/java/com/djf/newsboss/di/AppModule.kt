package com.djf.newsboss.di

import com.djf.newsboss.data.APIService
import com.djf.newsboss.domain.GetNewsUseCase
import com.djf.newsboss.domain.NewsRepository
import com.djf.newsboss.presentation.NewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl(): String = "https://newsdata.io/api/1/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepo(service: APIService): NewsRepository =
        com.djf.newsboss.data.repository.NewsRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository): GetNewsUseCase = GetNewsUseCase(repository)
    
}