package com.example.watchtvseries.data.local


import com.example.watchtvseries.data.model.FavoriteTvShow


class FavoriteTvShowsRepository (private val favoriteTVShowDao: FavoriteTVShowDao) {
    suspend fun addToFavorites(favoriteMovie: FavoriteTvShow) = favoriteTVShowDao.addToFavorites(favoriteMovie)
    fun getFavoriteTvShows() = favoriteTVShowDao.getFavoriteTvShows()
    suspend fun checkIfFavorite(id: Long) = favoriteTVShowDao.checkIfFavorite(id)
    suspend fun removeFromFavorites(id: Long) {
        favoriteTVShowDao.removeFromFavorites(id)
    }
}