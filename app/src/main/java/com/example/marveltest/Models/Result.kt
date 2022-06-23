package com.example.marveltest.Models
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Generated("jsonschema2pojo")

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Result (
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name : String,

    @Json(name = "description")
    val description : String,

    @Json(name = "thumbnail")
    val thumbnail: Thumbnail
) : Serializable
