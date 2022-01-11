package com.example.watchtvseries.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowAPIService {
    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): List<TVShow>
}