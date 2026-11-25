package com.example.tarotoneinviewmode.ui.iching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.iching.IChingData
import com.example.tarotoneinviewmode.data.repository.local.FavoritoRepository
import com.example.tarotoneinviewmode.domain.model.IChingHexagram
import com.example.tarotoneinviewmode.domain.model.Trigram
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class IChingArchiveUiState(
    val hexagrams: List<IChingHexagram> = emptyList(),
    val selectedTrigram: Trigram? = null,
    val isLoading: Boolean = false,
    val showOnlyFavorites: Boolean = false
)

@HiltViewModel
class IChingArchiveViewModel @Inject constructor(
    private val favoritoRepository: FavoritoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IChingArchiveUiState())
    val uiState: StateFlow<IChingArchiveUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
        carregarHexagramas()
    }

    private fun observeFavorites() {
        favoritoRepository.todosFavoritos.onEach {
            if (_uiState.value.showOnlyFavorites) {
                filtrar()
            }
        }.launchIn(viewModelScope)
    }

    private fun carregarHexagramas() {
        filtrar()
    }

    fun selecionarTrigram(trigram: Trigram?) {
        _uiState.update { it.copy(selectedTrigram = trigram, showOnlyFavorites = false) }
        filtrar()
    }

    fun toggleOnlyFavorites() {
        _uiState.update { it.copy(showOnlyFavorites = !it.showOnlyFavorites, selectedTrigram = null) }
        filtrar()
    }

    private fun filtrar() {
        viewModelScope.launch {
            val filteredList = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
                val state = _uiState.value
                var list = IChingData.hexagrams

                if (state.showOnlyFavorites) {
                    val favoritos = favoritoRepository.todosFavoritos.first()
                    val favoriteIds = favoritos.filter { it.tipo == "ICHING_HEXAGRAM" }.map { it.refId.toInt() }
                    list.filter { it.number in favoriteIds }
                } else if (state.selectedTrigram != null) {
                    list.filter { it.trigramUpper == state.selectedTrigram || it.trigramLower == state.selectedTrigram }
                } else {
                    list
                }
            }

            _uiState.update { it.copy(hexagrams = filteredList) }
        }
    }
}
