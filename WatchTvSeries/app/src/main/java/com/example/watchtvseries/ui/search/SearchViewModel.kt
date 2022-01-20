package com.example.watchtvseries.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchtvseries.data.model.SearchedShow
import com.example.watchtvseries.data.remote.TvShowsRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val tvShowsRepository: TvShowsRepository,) : ViewModel() {
    private val _searchedShows = MutableLiveData<List<SearchedShow>>()
    val searchedShows: LiveData<List<SearchedShow>>
    get() = _searchedShows

    fun searchQuery(params: String) {
        viewModelScope.launch {
            try {
                val searchQueryResult = tvShowsRepository.searchQuery(params)
                _searchedShows.postValue(searchQueryResult)
            } catch (ex: Exception) {
            }
        }
    }
}
