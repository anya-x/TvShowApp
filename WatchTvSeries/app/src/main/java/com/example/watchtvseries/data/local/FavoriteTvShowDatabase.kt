package com.example.watchtvseries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watchtvseries.data.local.FavoriteTVShowDao
import com.example.watchtvseries.data.model.FavoriteTvShow

@Database(
    entities = [FavoriteTvShow::class],
    version = 1,
)
abstract class FavoriteTvShowDatabase : RoomDatabase(){
    abstract fun getFavoriteTvShowDao(): FavoriteTVShowDao
}