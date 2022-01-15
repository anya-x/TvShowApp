package com.example.watchtvseries.data.remote
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class TvShowAPIBuilder {
    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("https://api.tvmaze.com/")
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE as Retrofit
        }

        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = Level.BODY }
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }


    }

    var tvShowAPIService: TvShowAPIService = getInstance().create(
        TvShowAPIService::class.java
    )
}