package com.example.marveltest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkUtils {
    companion object{
        val BASE_PATH = "https://gateway.marvel.com/"
        //val BASE_PATH = "https://jsonplaceholder.typicode.com/"

        fun getRetrofitInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

    }
}