package com.example.watchtvseries.model

import com.google.gson.annotations.SerializedName


class TvShowJsonData (
    @SerializedName("results")
    val results: ArrayList<TvShowJsonDataItem>
        )