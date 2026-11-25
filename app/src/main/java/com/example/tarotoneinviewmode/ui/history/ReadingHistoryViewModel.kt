package com.example.tarotoneinviewmode.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.local.LeituraEntity
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import com.example.tarotoneinviewmode.domain.model.draw.DrawCard
import com.example.tarotoneinviewmode.domain.model.draw.TarotReading
import com.example.tarotoneinviewmode.data.iching.IChingData
import com.example.tarotoneinviewmode.ui.draw.DrawPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadingHistoryViewModel @Inject constructor(
    private val leituraRepository: LeituraRepository,
    private val tarotRepository: TarotRepository
) : ViewModel() {

    private val _currentFilter = MutableStateFlow(HistoryFilter.ALL)

    val uiState: StateFlow<ReadingHistoryUiState> = combine(
        leituraRepository.todasLeituras,
        _currentFilter
    ) { leituras, filter ->
        val filteredLeituras = when (filter) {
            HistoryFilter.ALL -> leituras
            HistoryFilter.TAROT -> leituras.filter { it.tipo != "ICHING_DAY" && it.hexagramNumber == null }
            HistoryFilter.ICHING -> leituras.filter { it.tipo == "ICHING_DAY" || it.hexagramNumber != null }
            HistoryFilter.FAVORITES -> leituras.filter { it.isFavorite }
        }

        ReadingHistoryUiState(
            readings = filteredLeituras.mapNotNull { it.toUiItem() },
            currentFilter = filter
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ReadingHistoryUiState()
    )

    fun onFilterChanged(filter: HistoryFilter) {
        _currentFilter.value = filter
    }

    private suspend fun LeituraEntity.toUiItem(): ReadingHistoryUiItem? {
        // Se for uma leitura de I Ching
        if (tipo == "ICHING_DAY" || hexagramNumber != null) {
            val hexagramaObj = hexagramNumber?.let { num ->
                IChingData.hexagrams.firstOrNull { it.number == num }
            }
            val hexagramaFutureObj = hexagramFutureNumber?.let { num ->
                IChingData.hexagrams.firstOrNull { it.number == num }
            }
            
            return ReadingHistoryUiItem(
                id = id,
                tipo = tipo,
                timestamp = dataCriacao,
                note = nota ?: "",
                hexagramaNumber = hexagramNumber,
                hexagrama = hexagramaObj,
                hexagramaFutureNumber = hexagramFutureNumber,
                hexagramaFuture = hexagramaFutureObj,
                isFavorite = isFavorite
            )
        }

        // Caso seja leitura de Tarot
        val cartasEntity = leituraRepository.cartasDaLeitura(id)
        if (cartasEntity.isEmpty()) return null

        val drawCards = cartasEntity.mapNotNull { carta ->
            val tarotCard = tarotRepository.getCardById(carta.cardId) ?: return@mapNotNull null
            DrawCard(
                card = tarotCard,
                position = carta.posicao.toDrawPosition(),
                isReversed = carta.invertida
            )
        }

        val reading = TarotReading(
            id = id.toString(),
            cards = drawCards,
            timestamp = dataCriacao,
            note = nota ?: ""
        )

        return ReadingHistoryUiItem(
            id = id,
            tipo = tipo,
            timestamp = dataCriacao,
            note = nota ?: "",
            tarotReading = reading,
            isFavorite = isFavorite
        )
    }

    fun toggleFavorite(readingId: Long, currentFavorite: Boolean) {
        viewModelScope.launch {
            leituraRepository.setFavorito(readingId, !currentFavorite)
        }
    }

    fun updateNote(readingId: Long, note: String) {
        viewModelScope.launch {
            leituraRepository.atualizarNota(readingId, note)
        }
    }

    private fun String.toDrawPosition(): DrawPosition = try {
        DrawPosition.valueOf(this)
    } catch (e: Exception) {
        DrawPosition.UNKNOWN
    }

    fun deleteReading(readingId: Long, timestamp: Long, tipoLabel: String) {
        viewModelScope.launch {
            val entidade = LeituraEntity(
                id = readingId,
                tipo = tipoLabel,
                dataCriacao = timestamp
            )
            leituraRepository.deletarLeitura(entidade)
        }
    }
}