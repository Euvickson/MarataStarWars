package br.com.euvickson.maratastarwars.api

import br.com.euvickson.maratastarwars.api.model.StarWarsApiResults
import br.com.euvickson.maratastarwars.api.model.StarWarsPersonResult
import retrofit2.Call
import retrofit2.http.GET


interface StarWarsService {
    @GET("people/")
    fun listPerson(): Call<StarWarsApiResults>

    @GET("people/{id}")
    fun getPerson(id:Int): Call<StarWarsPersonResult>
}