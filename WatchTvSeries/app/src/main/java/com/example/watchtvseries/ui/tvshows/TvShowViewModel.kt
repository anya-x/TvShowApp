package com.example.watchtvseries.ui.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.watchtvseries.data.remote.TvShowPagingSource
import com.example.watchtvseries.data.remote.TvShowsRepository


class TvShowViewModel(private val tvShowsRepository: TvShowsRepository, ) : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = 250)) {
        TvShowPagingSource(tvShowsRepository)
    }.flow.cachedIn(viewModelScope)
}