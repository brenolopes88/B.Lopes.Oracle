package com.example.tarotoneinviewmode.ui.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import com.example.tarotoneinviewmode.data.repository.local.FavoritoRepository
import com.example.tarotoneinviewmode.domain.model.TarotCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val repository: TarotRepository,
    private val favoritoRepository: FavoritoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveUiState())
    val uiState: StateFlow<ArchiveUiState> = _uiState.asStateFlow()

    init {
        // Observa mudanças nos favoritos e atualiza a lista se necessário
        observeFavorites()
        loadCardsForTab(ArchiveTab.MAJOR)
    }

    private fun observeFavorites() {
        favoritoRepository.todosFavoritos.onEach { favoritos ->
            val currentTab = _uiState.value.selectedTab
            if (currentTab == ArchiveTab.FAVORITES) {
                val favoriteIds = favoritos.filter { it.tipo == "TAROT_CARD" }.map { it.refId }
                val favoriteCards = favoriteIds.mapNotNull { repository.getCardById(it) }
                _uiState.update { it.copy(cards = favoriteCards) }
            }
        }.launchIn(viewModelScope)
    }

    fun onTabSelected(tab: ArchiveTab) {
        _uiState.update { it.copy(selectedTab = tab, expandedCardId = null) }
        loadCardsForTab(tab)
    }

    fun onCardExpanded(cardId: String?) {
        _uiState.update { it.copy(expandedCardId = cardId) }
    }

    private fun loadCardsForTab(tab: ArchiveTab) {
        viewModelScope.launch {
            val filteredCards = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
                if (tab == ArchiveTab.FAVORITES) {
                    val favoritos = favoritoRepository.todosFavoritos.first()
                    val favoriteIds = favoritos.filter { it.tipo == "TAROT_CARD" }.map { it.refId }
                    favoriteIds.mapNotNull { repository.getCardById(it) }
                } else {
                    repository.getCardsByTab(tab)
                }
            }
            _uiState.update { it.copy(cards = filteredCards) }
        }
    }
}
