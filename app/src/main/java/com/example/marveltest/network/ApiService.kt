package com.example.marveltest.network


import com.example.marveltest.Models.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/public/characters?ts=1655511747473&apikey=982aa73aea2266c39c2cf93cea695e34&hash=723a0b0ec0c4050c7d836d82f6019788")
    fun getCharacters() : Call<Model>

    //@GET ("/v1/public/characters?{api}")
    //fun getModel(api:String) : Call<Model>

    @GET("posts")
    fun getPosts() : Call<List<Posts>>

}


