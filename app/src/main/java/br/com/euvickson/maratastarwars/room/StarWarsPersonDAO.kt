package br.com.euvickson.maratastarwars.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.euvickson.maratastarwars.model.StarWarsPerson

@Dao
interface StarWarsPersonDAO {

    @Query("SELECT * FROM starwarsperson")
    fun getAll(): List<StarWarsPerson>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg people: StarWarsPerson)
}