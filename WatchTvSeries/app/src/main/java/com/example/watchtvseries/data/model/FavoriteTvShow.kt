package com.example.watchtvseries.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.watchtvseries.util.Converters
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@TypeConverters(Converters::class)
@Entity(tableName = "fav_tvshow")
@Parcelize
data class FavoriteTvShow(
    var id_tvshow: Long,
    val name: String,
    @Embedded
    val image: Image?,
    val genres: List<String>,
    val summary: String
) : Serializable, Parcelable{
    @PrimaryKey (autoGenerate = true)
    var id : Int = 0
}