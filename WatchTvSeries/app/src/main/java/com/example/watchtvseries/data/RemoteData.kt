package com.example.watchtvseries.data

//import retrofit2.Response
import com.example.watchtvseries.model.TvShowJsonData
import retrofit2.Response
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val tvShowAPI: TvShowAPI
) {
    // TvShow wrapped in Retrofit.Response
    suspend fun getTvShow(queries: Map<String, String>): Response<TvShowJsonData> {
        return tvShowAPI.getTvShow(queries)
    }
}