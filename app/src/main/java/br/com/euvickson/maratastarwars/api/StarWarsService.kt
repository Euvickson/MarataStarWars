package br.com.euvickson.maratastarwars.api

import br.com.euvickson.maratastarwars.api.model.StarWarsApiResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface StarWarsService {
    @GET("people/")
    suspend fun listFirstPage(): StarWarsApiResult

    companion object {
        var starWarsService: StarWarsService? = null

        fun getInstance(): StarWarsService {
            if (starWarsService == null) {
                starWarsService = Retrofit
                    .Builder()
                    .baseUrl("https://swapi.dev/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(StarWarsService::class.java)
            }
            return starWarsService!!
        }
    }
}