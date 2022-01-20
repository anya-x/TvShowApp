package com.example.watchtvseries.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    val id: Long,
    val name: String,
    val image: Image?,
    val genres: List<String>,
    val summary: String
): Parcelable