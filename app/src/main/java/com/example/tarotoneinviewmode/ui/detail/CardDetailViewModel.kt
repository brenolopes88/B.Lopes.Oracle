package com.example.tarotoneinviewmode.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import com.example.tarotoneinviewmode.data.repository.local.FavoritoRepository
import com.example.tarotoneinviewmode.domain.model.TarotCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CardDetailUiState(
    val card: TarotCard? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val isFavorite: Boolean = false
)

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val repository: TarotRepository,
    private val favoritoRepository: FavoritoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cardId: String = savedStateHandle["cardId"] ?: ""

    private val _uiState = MutableStateFlow(CardDetailUiState())
    val uiState: StateFlow<CardDetailUiState> = _uiState.asStateFlow()

    init {
        loadCard()
        observeFavorite()
    }

    private fun loadCard() {
        val card = repository.getCardById(cardId)
        _uiState.update { 
            if (card != null) {
                it.copy(card = card, isLoading = false)
            } else {
                it.copy(card = null, isLoading = false, error = "Carta não encontrada")
            }
        }
    }

    private fun observeFavorite() {
        viewModelScope.launch {
            favoritoRepository.ehFavorito("TAROT_CARD", cardId).collect { ehFavorito ->
                _uiState.update { it.copy(isFavorite = ehFavorito) }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            favoritoRepository.alternarFavorito("TAROT_CARD", cardId, !_uiState.value.isFavorite)
        }
    }
}
