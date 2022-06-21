package com.example.marveltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.work.Data
import com.example.marveltest.Models.Model
import com.example.marveltest.Models.ModelMarvel
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
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList
import com.google.gson.GsonBuilder as GsonGsonBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val mutableLiveData = MutableLiveData<List<Result>>()
    val dataCharacter : LiveData<List<Result>> = mutableLiveData


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

        //val apiValue = Autenticator().setApiInfo()

        val callback = endpoint
            .getCharacters()

        //val mapType = object : TypeToken<Map<String, Any>>() {}.type


        callback.enqueue(object :  retrofit2.Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                println("foi")
                println("debugg")
                if(response.isSuccessful){
                    val listItems: MutableList<ModelMarvel> = mutableListOf()

                    response.body()?.let { teste ->
                        mutableLiveData.value = teste.responseData.responseResult

                    }
                    var aux: String? = null
                    //val erroList: Result? = mutableLiveData.value?.get(10)
                    val erroListComArray : List<Result>? = mutableLiveData.value

                    for(result: Result in erroListComArray!!){
                        println(result.description)
                        aux += result.description + "\n"

                    }

                    binding.textViewApi.text = aux

                    println("chegamos aqui")
                }else {
                    println("Deu fezes")
                    println("response.message()")
                }




            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                println("não foi")
                println(t)
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


