package com.example.marveltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marveltest.Models.Model
import com.example.marveltest.Models.Posts
import com.example.marveltest.Models.Result

import com.example.marveltest.databinding.ActivityMainBinding
import com.example.marveltest.network.ApiService
import com.example.marveltest.network.Autenticator
import com.example.marveltest.network.NetworkUtils
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList
import com.google.gson.GsonBuilder as GsonGsonBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

    }

    fun getData(){


        val retrofitClient = NetworkUtils
            .getRetrofitInstance()

        val endpoint = retrofitClient
            .create(ApiService::class.java)

        val apiValue = Autenticator().setApiInfo()

        val callback = endpoint
            .getResult()

        val mapType = object : TypeToken<Map<String, Any>>() {}.type

        callback.enqueue(object :  retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                println("foi")
                //val json = """{"title": "Kotlin Tutorial", "author": "bezkoder", "categories" : ["Kotlin","Basic"]}"""
                val gson1 = Gson()
                var tutorialMap: Map<String, Any> = gson1.fromJson(response.body(), object : TypeToken<Map<String, Any>>() {}.type)
                tutorialMap.forEach { println(it) }

             //   println("renan Deus")

                //println(tutorialMap.get("data"))
                val mapadomapa: LinkedTreeMap<String,Any> = tutorialMap.get("data") as LinkedTreeMap<String, Any>

               // println(mapadomapa.get("results"))
               // println("renan Deus, é maior")

                val personagensMap: ArrayList<String> = mapadomapa.get("results") as ArrayList<String>

               // println("renan Deus, é maiorm, muito maior")
              //  println(personagensMap.indexOf("id=1011334"))

                //println("renan Deus, é maiorm, muito maior, ta ficando bom")

                val interadorQualquer = personagensMap.iterator()

                while (interadorQualquer.hasNext()){
                    println(interadorQualquer.next())
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("não foi")
            }


        } )


    }




    private fun configView(){
        val tabLayout = binding.addTabLayout
        val viewPager = binding.addViewpager

        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter
    }

    class TabViewPagerAdapter(fa: FragmentActivity) :FragmentStateAdapter(fa) {
        val abas = arrayOf(R.string.home, R.string.list_fav)
        val fragment = arrayOf(HomeFragment(), ListFavFragment())

        override fun getItemCount(): Int {
            return fragment.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragment[position]
        }

        class HomeFragment : Fragment() {}

        class ListFavFragment : Fragment() {}



    }


}


