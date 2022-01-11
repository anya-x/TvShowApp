package com.example.watchtvseries.data.remote

import org.json.JSONArray

data class TVShow(
    val id: Long,
    val name: String,
    val genres: String,
    val image: JSONArray,
    val summary: String
){

}