package br.com.euvickson.maratastarwars.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarWarsPerson (
    @PrimaryKey
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
    val url: String,
    var isFavorite: Boolean = false
        )