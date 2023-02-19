package br.com.euvickson.maratastarwars.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euvickson.maratastarwars.Repository
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import br.com.euvickson.maratastarwars.room.StarWarsPersonDAO
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var starWarsPersonListResponse = MutableLiveData<List<StarWarsPerson>>()

    var errorMessage: String by mutableStateOf("")
    init {
        starWarsPersonListResponse = Repository.getList()!!
    }

    fun getStarWarsPeopleListData(): LiveData<List<StarWarsPerson>> {
        return starWarsPersonListResponse
    }
}