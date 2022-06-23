package com.example.marveltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter

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

    var listOfName : List<String> = emptyList()
    var listOfID :  List<String> = emptyList()
    var listOfThumbnail : List<String> = emptyList()
    var listOfDescripton : List<String> = emptyList()




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

        val callback = endpoint
            .getCharacters()

        callback.enqueue(object :  retrofit2.Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                if(response.isSuccessful){
                    val listItems: MutableList<ModelMarvel> = mutableListOf()

                    response.body()?.let { teste ->
                        mutableLiveData.value = teste.responseData.responseResult
                    }
                    var aux: String? = null
                    //val erroList: Result? = mutableLiveData.value?.get(10)
                    val erroListComArray : List<Result>? = mutableLiveData.value
                  //  var listTest: List<String> = emptyList()
                    for(result: Result in erroListComArray!!){
                        println(result.name)
                        listOfName += result.name
                        listOfID += result.id
                        listOfThumbnail += result.thumbnail.path + "." + result.thumbnail.extension
                        listOfDescripton += result.description
                        aux += result.description + "\n"
                        //listTest += "name: " + listOfName +"\n" + "Description of " + listOfName + ":" + listOfDescripton + "\n"
                    }



                    testBigString()





                    println("aqui")



                }else {
                    println("response.message()")
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                println("Erro, erro:")
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

        class HomeFragment : Fragment() {

        }

        class ListFavFragment : Fragment() {}



    }

    fun testBigString(){

        binding.textViewTest.text = "name of caracter:" + listOfName[0] + "\nDescription of caracter:"+ listOfDescripton[0] +"\n\nname of caracter: "+ listOfName[1] + "\nDescription of caracter:"+ listOfDescripton[1] + "\n\nname of caracter: "+ listOfName[2] + "\nDescription of caracter:"+ listOfDescripton[2] + "\n\nname of caracter: "+ listOfName[10] + "\nDescription of caracter:"+ listOfDescripton[10] + "\nThumb path of Caracter:" + listOfThumbnail[10]
    }

}


