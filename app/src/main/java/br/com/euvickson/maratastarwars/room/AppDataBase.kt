package br.com.euvickson.maratastarwars.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.euvickson.maratastarwars.model.StarWarsPerson

@TypeConverters(Converter::class)
@Database(
    entities = [StarWarsPerson::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun starWarsDAO(): StarWarsPersonDAO

}