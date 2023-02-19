package br.com.euvickson.maratastarwars.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<StarWarsPerson>())

    val state:StateFlow<List<StarWarsPerson>>
        get() = _state

    init {
        viewModelScope.launch {
            val personList = StarWarsService.getInstance().listFirstPage().results
            _state.value = personList
        }
    }
}