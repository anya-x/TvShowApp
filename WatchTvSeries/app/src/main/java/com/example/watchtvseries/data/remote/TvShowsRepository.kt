package com.example.watchtvseries.data.remote

import com.example.watchtvseries.api.TvShowAPIService
import com.example.watchtvseries.data.model.SearchedShow
import com.example.watchtvseries.data.model.TVShow

class TvShowsRepository(private val tvShowAPIService: TvShowAPIService) {
    suspend fun getShows(page: Int): List<TVShow> {
        return tvShowAPIService.getShows(page)
    }

    suspend fun searchQuery(param: String): List<SearchedShow> {
        return tvShowAPIService.searchQuery(param)
    }

}