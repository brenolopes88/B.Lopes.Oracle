package com.example.tarotoneinviewmode.ui.iching

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.iching.IChingData
import com.example.tarotoneinviewmode.data.repository.local.FavoritoRepository
import com.example.tarotoneinviewmode.domain.model.IChingHexagram
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HexagramDetailUiState(
    val hexagram: IChingHexagram? = null,
    val isFavorite: Boolean = false
)

@HiltViewModel
class HexagramDetailViewModel @Inject constructor(
    private val favoritoRepository: FavoritoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val number: Int = savedStateHandle["number"] ?: 1

    private val _uiState = MutableStateFlow(HexagramDetailUiState())
    val uiState: StateFlow<HexagramDetailUiState> = _uiState.asStateFlow()

    init {
        loadHexagram()
        observeFavorite()
    }

    private fun loadHexagram() {
        val hexagram = IChingData.hexagrams.firstOrNull { it.number == number }
        _uiState.update { it.copy(hexagram = hexagram) }
    }

    private fun observeFavorite() {
        viewModelScope.launch {
            favoritoRepository.ehFavorito("ICHING_HEXAGRAM", number.toString()).collect { ehFavorito ->
                _uiState.update { it.copy(isFavorite = ehFavorito) }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            favoritoRepository.alternarFavorito("ICHING_HEXAGRAM", number.toString(), !_uiState.value.isFavorite)
        }
    }
}
