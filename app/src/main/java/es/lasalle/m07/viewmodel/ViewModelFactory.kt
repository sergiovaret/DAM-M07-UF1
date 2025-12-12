package es.lasalle.m07.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.lasalle.m07.data.repository.RocketsRepository

class ViewModelFactory(private val repository: RocketsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RocketsViewModel(repository) as T
            //return RocketsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
