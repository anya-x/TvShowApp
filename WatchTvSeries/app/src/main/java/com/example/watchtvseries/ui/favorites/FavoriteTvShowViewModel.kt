package com.example.watchtvseries.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.watchtvseries.data.local.FavoriteTvShowsRepository

class FavoriteTvShowViewModel(private val favoriteTvShowsRepository: FavoriteTvShowsRepository): ViewModel() {
        val favTvShows = favoriteTvShowsRepository.getFavoriteTvShows()


}