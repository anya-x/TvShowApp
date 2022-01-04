package com.example.watchtvseries

import com.example.watchtvseries.Model.TvShowJsonData
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val tvShowAPI: TvShowAPI
) {
    // TvShow wrapped in Retrofit.Response
    suspend fun getTvShow(queries: Map<String, String>) {
        tvShowAPI.getTvShow(queries) }
}