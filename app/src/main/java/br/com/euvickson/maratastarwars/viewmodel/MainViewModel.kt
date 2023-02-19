package br.com.euvickson.maratastarwars.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var starWarsPersonListResponse:List<StarWarsPerson> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")

    fun getPeopleList() {
        viewModelScope.launch {
            val starWarsService = StarWarsService.getInstance()
            try {
                val personList = starWarsService.listFirstPage().results
                starWarsPersonListResponse = personList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun insertDataBase () {

    }


}