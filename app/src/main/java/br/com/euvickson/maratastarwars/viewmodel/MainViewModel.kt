package br.com.euvickson.maratastarwars.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import br.com.euvickson.maratastarwars.room.StarWarsPersonDAO
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var starWarsPersonListResponse:List<StarWarsPerson> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")

    fun getPeopleList(dao: StarWarsPersonDAO) {
        viewModelScope.launch {
            val starWarsService = StarWarsService.getInstance()
            try {
                val personList = starWarsService.listFirstPage().results
                starWarsPersonListResponse = personList
                insertDataBase(dao)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun insertDataBase(dao: StarWarsPersonDAO) {
        starWarsPersonListResponse.forEach {
        dao.insertAll(it)
        }
    }
}