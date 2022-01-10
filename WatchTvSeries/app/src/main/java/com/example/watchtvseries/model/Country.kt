package com.example.watchtvseries.model


import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: String
)