package com.example.watchtvseries.data.remote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TvShowAPIBuilder {
    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://api.tvmaze.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE as Retrofit
        }
    }

    var tvShowAPIService: TvShowAPIService = getInstance().create(
        TvShowAPIService::class.java
    )
}