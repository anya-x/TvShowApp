package com.example.watchtvseries.data
import com.example.watchtvseries.model.TvShowJsonData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap



// request data from the endpoint of "shows" via GET
// with a suspend function which makes the function
// run in a background thread instead of main thread
interface TvShowAPI {
    @GET("/shows/")
    suspend fun getTvShow(
        @QueryMap queries: Map<String, String>
    ):Response<TvShowJsonData>
}