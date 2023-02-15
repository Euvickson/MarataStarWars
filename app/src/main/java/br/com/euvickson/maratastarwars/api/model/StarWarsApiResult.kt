package br.com.euvickson.maratastarwars.api.model

import br.com.euvickson.maratastarwars.model.StarWarsPerson

data class StarWarsApiResults (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarWarsPerson>
        )
