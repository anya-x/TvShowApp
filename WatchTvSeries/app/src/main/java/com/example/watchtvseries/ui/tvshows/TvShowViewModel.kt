package com.example.watchtvseries.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.repository.TvShowsRepository
import kotlinx.coroutines.launch


class TvShowViewModel(private val tvShowsRepository: TvShowsRepository,
) : ViewModel() {

    private val _shows = MutableLiveData<List<TVShow>>()
    val shows: LiveData<List<TVShow>>
        get() = _shows

    val flow = Pager(
        PagingConfig(pageSize = 250)
    ) {
        TvShowPagingSource(tvShowsRepository)
    }.flow
        .cachedIn(viewModelScope)

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