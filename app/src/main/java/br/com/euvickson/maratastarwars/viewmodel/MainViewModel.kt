package br.com.euvickson.maratastarwars.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import br.com.euvickson.maratastarwars.room.AppDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val list = MutableStateFlow(emptyList<StarWarsPerson>())

    val listFlow: StateFlow<List<StarWarsPerson>>
        get() = list

    init {
        viewModelScope.launch {
            val personResponse = StarWarsService.getInstance().listFirstPage().results
            list.value += personResponse
        }
    }

    fun insertDataIntoDb (context: Context) {
        val dao = AppDataBase.getInstance(context)
        list.value.forEach {
            dao.insertAll(it)
        }
    }
}
