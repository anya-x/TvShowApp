package com.example.watchtvseries.dependencyinject

import com.example.watchtvseries.Constant.Companion.BASE_URL
import com.example.watchtvseries.TvShowAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


// providing all the dependencies for getting our TvMaze API
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }
    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpCli : OkHttpClient,
        gsonConverterFactory : GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpCli)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Singleton
    @Provides
    fun giveApiService(retrofit: Retrofit): TvShowAPI {
        return retrofit.create(TvShowAPI::class.java)
    }
}