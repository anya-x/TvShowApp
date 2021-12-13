package com.example.watchtvseries.Model


import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: String
)