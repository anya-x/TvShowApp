package com.example.watchtvseries.ui.details

import androidx.lifecycle.ViewModel
import com.example.watchtvseries.data.local.FavoriteTvShowsRepository
import com.example.watchtvseries.data.model.FavoriteTvShow
import com.example.watchtvseries.data.model.TVShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class DetailViewModel(private val favoriteTvShowsRepository: FavoriteTvShowsRepository) : ViewModel() {

    fun addToFavorites(tvShow: TVShow){
        Log.d("debug:","added to fav")
        CoroutineScope(Dispatchers.IO).launch {
            favoriteTvShowsRepository.addToFavorites(
                FavoriteTvShow(
                    tvShow.id,
                    tvShow.name,
                    tvShow.image,
                    tvShow.genres,
                    tvShow.summary
                )
            )
            Log.d("debug:","added to fav done")
        }
    }

    suspend fun checkIfFavorite(id: Long) = favoriteTvShowsRepository.checkIfFavorite(id)

    fun removeFromFavorites(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            favoriteTvShowsRepository.removeFromFavorites(id)
        }
    }

}