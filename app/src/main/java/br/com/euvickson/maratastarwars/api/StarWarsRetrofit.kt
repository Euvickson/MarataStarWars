package br.com.euvickson.maratastarwars.api

import android.util.Log
import br.com.euvickson.maratastarwars.api.model.StarWarsApiResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StarWarsRetrofit {

    private val service: StarWarsService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(StarWarsService::class.java)
    }

    fun listStarWarsPerson () {
        val call = service.listPerson()

        call.enqueue(object : Callback<StarWarsApiResults> {
            override fun onResponse(
                call: Call<StarWarsApiResults>,
                response: Response<StarWarsApiResults>
            ) {
                Log.i("STAR_WARS_API", "Star Wars People Loaded")
            }

            override fun onFailure(call: Call<StarWarsApiResults>, t: Throwable) {
                Log.e("STAR_WARS_API", "Error during API request", t)
            }

        })
    }
}