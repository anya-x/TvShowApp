package com.example.watchtvseries.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.watchtvseries.data.model.FavoriteTvShow

@Dao
interface FavoriteTVShowDao {
    @Insert
    suspend fun addToFavorites(favoriteTvShow: FavoriteTvShow)

    @Query("SELECT * FROM fav_tvshow")
    fun getFavoriteTvShows(): LiveData<List<FavoriteTvShow>>

    @Query("SELECT * FROM fav_tvshow")
    fun getFavoriteTvShow(): LiveData<List<FavoriteTvShow>>

    @Query("SELECT count(*) FROM fav_tvshow WHERE fav_tvshow.id_tvshow = :id")
    suspend fun checkIfFavorite(id: Long): Int

    @Query("DELETE FROM fav_tvshow WHERE fav_tvshow.id_tvshow = :id" )
    suspend fun removeFromFavorites(id: Long) : Int
}