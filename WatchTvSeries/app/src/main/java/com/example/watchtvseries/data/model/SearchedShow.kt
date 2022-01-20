package com.example.watchtvseries.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchedShow(
    val score: Double,
    val show: TVShow
) : Parcelable