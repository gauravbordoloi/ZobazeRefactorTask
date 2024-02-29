package com.zobaze.zobazerefractortask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zobaze.zobazerefractortask.repository.network.EmployeeApi
import com.zobaze.zobazerefractortask.repository.network.EmployeeApiNetworkImpl
import com.zobaze.zobazerefractortask.repository.network.NetworkEmployeeApiV1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class) //required as singleton
@Module
object NetworkModule {

    private const val BASE_URL = "https://dummy.restapiexample.com/api/"

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideEmployeeV1Api(retrofit: Retrofit): NetworkEmployeeApiV1 {
        return retrofit.create(NetworkEmployeeApiV1::class.java)
    }

    @Provides
    @Singleton
    fun provideEmployeeApi(networkEmployeeApiV1: NetworkEmployeeApiV1): EmployeeApi {
        return EmployeeApiNetworkImpl(networkEmployeeApiV1)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15L, TimeUnit.SECONDS)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}