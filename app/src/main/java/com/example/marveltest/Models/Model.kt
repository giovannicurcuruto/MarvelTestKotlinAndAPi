package com.example.marveltest.Models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model (
    @Json(name = "data")
    val responseData: Data
)