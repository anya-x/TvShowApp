package com.example.watchtvseries.Model


import com.google.gson.annotations.SerializedName

data class Externals(
    @SerializedName("imdb")
    val imdb: Any,
    @SerializedName("thetvdb")
    val thetvdb: Any,
    @SerializedName("tvrage")
    val tvrage: Int
)