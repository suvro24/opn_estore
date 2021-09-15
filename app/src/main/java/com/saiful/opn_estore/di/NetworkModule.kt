package com.saiful.opn_estore.di

import com.saiful.opn_estore.api.SwaggerAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val BASE_URL = "https://virtserver.swaggerhub.com/m-tul/opn-mobile-challenge-api/1.0.0/"

    @Singleton
    @Provides
    fun provideRetrofitService(): SwaggerAPIService {

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwaggerAPIService::class.java)
    }
}
