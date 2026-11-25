package com.example.tarotoneinviewmode.ui.draw

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import com.example.tarotoneinviewmode.domain.model.draw.DrawCard
import com.example.tarotoneinviewmode.domain.model.draw.TarotReading
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawViewModel @Inject constructor(
    private val repository: TarotRepository,
    private val leituraRepository: LeituraRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val modeArg: String = savedStateHandle["mode"] ?: NavRoute.Draw.DAY_CARD
    private val mode = when (modeArg) {
        NavRoute.Draw.PPF -> DrawMode.PAST_PRESENT_FUTURE
        NavRoute.Draw.SIM_NAO -> DrawMode.SIM_NAO
        "desafio_solucao" -> DrawMode.DESAFIO_SOLUCAO
        "relacionamento" -> DrawMode.RELACIONAMENTO
        else -> DrawMode.DAY_CARD
    }

    private val _uiState = mutableStateOf(DrawUiState(mode = mode))
    val uiState: State<DrawUiState> = _uiState

    init {
        if (mode == DrawMode.DAY_CARD) {
            verificarCartaDoDia()
        }
    }

    private fun verificarCartaDoDia() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCheckingDay = true)

            val leituraHoje = leituraRepository.leituraDoDiaCompleta()
            if (leituraHoje != null) {
                // reconstrói os DrawCards a partir do banco
                val drawCards = leituraHoje.second.mapNotNull { carta ->
                    val tarotCard = repository.getCardById(carta.cardId) ?: return@mapNotNull null
                    DrawCard(
                        card = tarotCard,
                        position = DrawPosition.DAY,
                        isReversed = carta.invertida
                    )
                }
                if (drawCards.isNotEmpty()) {
                    _uiState.value = DrawUiState(
                        mode = mode,
                        cards = drawCards,
                        revealedCount = 1,      // já revelada
                        isComplete = true,
                        isCheckingDay = false,
                        cartaDoDiaJaExistia = true
                    )
                    return@launch
                }
            }

            _uiState.value = _uiState.value.copy(isCheckingDay = false)
        }
    }

    fun drawCards() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isShuffling = true)
            AnalyticsHelper.logDrawStarted(mode.name.lowercase())

            // Simula o tempo de embaralhar
            delay(1500)

            val positions = when (mode) {
                DrawMode.DAY_CARD -> listOf(DrawPosition.DAY)
                DrawMode.PAST_PRESENT_FUTURE -> listOf(
                    DrawPosition.PAST,
                    DrawPosition.PRESENT,
                    DrawPosition.FUTURE
                )
                DrawMode.SIM_NAO -> listOf(DrawPosition.ANSWER)
                DrawMode.DESAFIO_SOLUCAO -> listOf(DrawPosition.CHALLENGE, DrawPosition.SOLUTION)
                DrawMode.RELACIONAMENTO -> listOf(DrawPosition.YOU, DrawPosition.THE_OTHER, DrawPosition.CONNECTION)
            }

            val allCards = repository.getAllCards().toMutableList()
            val drawnCards = positions.map { position ->
                val index = (allCards.indices).random()
                val card = allCards.removeAt(index)
                DrawCard(
                    card = card,
                    position = position,
                    isReversed = false
                )
            }

            // salva em memória — compatibilidade com o fluxo existente
            val reading = TarotReading(cards = drawnCards)
            repository.saveReading(reading)

            // salva no Room — persiste entre sessões
            leituraRepository.salvarLeitura(
                tipo = mode.name,
                cartas = drawnCards.map { drawCard ->
                    Pair(drawCard.card.id, drawCard.position.name)
                }
            )

            // 🚀 Analytics
            AnalyticsHelper.logTarotDrawCompleted(mode.name)

            _uiState.value = DrawUiState(
                mode = mode,
                cards = drawnCards,
                revealedCount = 0,
                isComplete = false,
                isShuffling = false
            )
        }
    }

    fun revealNext() {
        val state = _uiState.value
        if (state.revealedCount < state.cards.size) {
            val newCount = state.revealedCount + 1
            _uiState.value = state.copy(
                revealedCount = newCount,
                isComplete = newCount >= state.cards.size
            )
        }
    }

    fun reset() {
        _uiState.value = DrawUiState(mode = mode)
    }
}