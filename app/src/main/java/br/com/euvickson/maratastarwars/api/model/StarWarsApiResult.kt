package br.com.euvickson.maratastarwars.api.model

data class StarWarsApiResults (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarWarsPersonResult>
        )

data class StarWarsPersonResult (
    val name: String,
    val height: Int,
    val mass: Int,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val url: String
        )
