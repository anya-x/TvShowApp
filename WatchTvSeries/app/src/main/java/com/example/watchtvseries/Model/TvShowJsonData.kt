package com.example.watchtvseries.Model

import com.google.gson.annotations.SerializedName


class TvShowJsonData (
    @SerializedName("results")
    val results: ArrayList<TvShowJsonDataItem>
        )