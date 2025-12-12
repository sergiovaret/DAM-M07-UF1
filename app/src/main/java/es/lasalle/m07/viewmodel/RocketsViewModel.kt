package es.lasalle.m07.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.lasalle.m07.data.local.RocketEntity
import es.lasalle.m07.data.repository.RocketsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RocketsViewModel(private val repository: RocketsRepository) : ViewModel() {
    val rockets: StateFlow<List<RocketEntity>> = repository.rockets
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun refreshRockets() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.refreshRockets()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al refrescar los cohetes"
            } finally {
                _loading.value = false
            }
        }
    }
}