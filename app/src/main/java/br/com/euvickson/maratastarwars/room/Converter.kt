package br.com.euvickson.maratastarwars.room

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun listToString(listaEspecies: List<String>): String {
        return listaEspecies[0]
    }

    @TypeConverter
    fun stringToList(string: String): List<String> {
        return  listOf(string)
    }
}