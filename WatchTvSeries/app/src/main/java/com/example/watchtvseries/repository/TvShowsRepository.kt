package com.example.watchtvseries.repository

import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.data.remote.TvShowAPIService

class TvShowsRepository(private val tvShowAPIService: TvShowAPIService) {
        suspend fun getShows(page: Int): List<TVShow> {
            return tvShowAPIService.getShows(page)
        }
}