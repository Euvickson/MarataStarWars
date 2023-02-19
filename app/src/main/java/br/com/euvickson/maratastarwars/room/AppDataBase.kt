package br.com.euvickson.maratastarwars.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.euvickson.maratastarwars.model.StarWarsPerson

@Database(entities = [StarWarsPerson::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun starWarsDAO(): StarWarsPersonDAO
}