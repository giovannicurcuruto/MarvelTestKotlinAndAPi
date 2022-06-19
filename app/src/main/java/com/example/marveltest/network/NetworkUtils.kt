package com.example.marveltest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object{
        val BASE_PATH = "https://gateway.marvel.com"
        //val BASE_PATH = "https://jsonplaceholder.typicode.com/"

        fun getRetrofitInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}