package br.com.euvickson.maratastarwars.api

import br.com.euvickson.maratastarwars.api.model.StarWarsApiResults
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import retrofit2.Call
import retrofit2.http.GET


interface StarWarsService {
    @GET("people/")
    fun listFirstPage(): Call<StarWarsApiResults>

    @GET("people/{id}")
    fun getPerson(id:Int): Call<StarWarsPerson>

    @GET("people/?page={number}")
    fun listPageApi(number: Int): Call<StarWarsApiResults>
}