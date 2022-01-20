package com.example.watchtvseries.api

import com.example.watchtvseries.data.model.SearchedShow
import com.example.watchtvseries.data.model.TVShow
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowAPIService {
    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): List<TVShow>

    @GET("search/shows")
    suspend fun searchQuery(@Query("q") param: String): List<SearchedShow>

}