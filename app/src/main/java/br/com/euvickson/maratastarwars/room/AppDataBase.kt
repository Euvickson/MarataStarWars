package br.com.euvickson.maratastarwars.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.euvickson.maratastarwars.model.StarWarsPerson

@Database(entities = [StarWarsPerson::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun starWarsDAO(): StarWarsPersonDAO

    companion object {
        @Volatile
        private var db: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return db ?: Room
                .databaseBuilder(context, AppDataBase::class.java, "starwars-db")
                .addMigrations()
                .build()
        }
    }
}