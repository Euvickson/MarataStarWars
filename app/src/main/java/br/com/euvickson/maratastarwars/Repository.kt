package br.com.euvickson.maratastarwars

import androidx.lifecycle.MutableLiveData
import br.com.euvickson.maratastarwars.api.StarWarsService
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository {

    companion object {
        fun getList(): MutableLiveData<List<StarWarsPerson>>? {
            val starWarsPerson: MutableLiveData<List<StarWarsPerson>> = MutableLiveData<List<StarWarsPerson>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch (Dispatchers.IO) {
                    val service = StarWarsService.getInstance()
                    var response = service.listFirstPage()

                    withContext(Dispatchers.Default) {
                        response.let {
                            starWarsPerson.postValue(response.results)
                        }
                    }
                }
            }
            return starWarsPerson
        }
    }
}