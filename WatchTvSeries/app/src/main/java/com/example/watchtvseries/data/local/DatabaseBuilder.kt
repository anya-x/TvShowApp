package com.example.watchtvseries.data.local

import android.content.Context
import androidx.room.Room

class DataBaseBuilder {
    companion object {
        var favoriteTVShowDao: FavoriteTVShowDao? = null
        @JvmStatic
        fun getInstance(context: Context): FavoriteTVShowDao {
            return favoriteTVShowDao ?: getDao(context)
        }

        private fun getDao(context: Context): FavoriteTVShowDao {
            favoriteTVShowDao = Room.databaseBuilder(
                context.applicationContext,
                FavoriteTvShowDatabase::class.java,
                "fav_db"
            )
                .allowMainThreadQueries()
                .build()
                .getFavoriteTvShowDao()
            return favoriteTVShowDao as FavoriteTVShowDao
        }
    }
}
/**class DatabaseBuilder {
     fun getFavTvShowDatabase(context: Context) = Room.databaseBuilder(
         context.applicationContext,
         FavoriteTvShowDatabase::class.java,
         "tvshow_db")
         .allowMainThreadQueries()
         .build()

    fun getDao(db: FavoriteTvShowDatabase): FavoriteTVShowDao {
         return db.getFavoriteTvShowDao()
     }

}**/