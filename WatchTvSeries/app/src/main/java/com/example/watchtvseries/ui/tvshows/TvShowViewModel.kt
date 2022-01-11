package com.example.watchtvseries.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.repository.TvShowsRepository
import kotlinx.coroutines.launch


class TvShowViewModel(
    val tvShowsRepository: TvShowsRepository,
) : ViewModel() {

    private val _shows = MutableLiveData<List<TVShow>>()
    val shows: LiveData<List<TVShow>>
        get() = _shows


    fun getShows(page: Int) {
        viewModelScope.launch {
            try {
                val result = tvShowsRepository.getShows(page)
                _shows.postValue(result)
            } catch (ex: Exception) {
            }
        }

    }
}