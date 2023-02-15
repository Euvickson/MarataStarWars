package br.com.euvickson.maratastarwars.model

data class StarWarsPerson (
    val name: String,
    val height: Int,
    val mass: Int,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val species: List<String>,
    val url: String
        )