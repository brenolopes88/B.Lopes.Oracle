package com.example.tarotoneinviewmode.ui.iching

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.iching.IChingData
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import com.example.tarotoneinviewmode.domain.model.IChingHexagram
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class IChingMode(val title: String) {
    GENERAL("Consulta de Sabedoria"),
    MUTATION("Consulta de Mutação"),
    DAILY("Hexagrama do Dia")
}

sealed class DrawState {
    object Intention : DrawState()
    object FlippingCoins : DrawState()
    data class Result(
        val hexagram: IChingHexagram, 
        val secondHexagram: IChingHexagram? = null,
        val isSaved: Boolean = false
    ) : DrawState()
}

data class IChingDrawUiState(
    val mode: IChingMode = IChingMode.GENERAL,
    val drawState: DrawState = DrawState.Intention,
    val hexagramDoDia: IChingHexagram? = null,
    val pergunta: String = ""
)

@HiltViewModel
class IChingDrawViewModel @Inject constructor(
    private val leituraRepository: LeituraRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val modeArg: String = savedStateHandle["mode"] ?: NavRoute.IChing.MODE_GENERAL
    private val currentMode = when (modeArg) {
        NavRoute.IChing.MODE_MUTATION -> IChingMode.MUTATION
        NavRoute.IChing.MODE_DAILY -> IChingMode.DAILY
        else -> IChingMode.GENERAL
    }

    private val _uiState = MutableStateFlow(IChingDrawUiState(mode = currentMode))
    val uiState: StateFlow<IChingDrawUiState> = _uiState.asStateFlow()

    init {
        // Se for modo diário, verifica se já existe leitura hoje
        if (currentMode == IChingMode.DAILY) {
            verificarLeituraExistente()
        }
    }

    private fun verificarLeituraExistente() {
        viewModelScope.launch {
            val leituraDoDia = leituraRepository.leituraIChingDoDia()
            if (leituraDoDia != null && leituraDoDia.hexagramNumber != null) {
                val hexagram = IChingData.hexagrams.firstOrNull { it.number == leituraDoDia.hexagramNumber }
                if (hexagram != null) {
                    // 🚀 Analytics
                    AnalyticsHelper.logIChingDailyViewed()

                    _uiState.update {
                        it.copy(
                            drawState = DrawState.Result(hexagram = hexagram, isSaved = true),
                            hexagramDoDia = hexagram
                        )
                    }
                }
            }
        }
    }

    fun onPerguntaChanged(novaPergunta: String) {
        _uiState.update { it.copy(pergunta = novaPergunta) }
    }

    fun realizarSorteio() {
        viewModelScope.launch {
            AnalyticsHelper.logDrawStarted(currentMode.name.lowercase())
            _uiState.update { it.copy(drawState = DrawState.FlippingCoins) }

            delay(2000)

            val sorteado = IChingData.hexagrams.random()
            
            var segundoSorteado: IChingHexagram? = null
            if (currentMode == IChingMode.MUTATION) {
                do {
                    segundoSorteado = IChingData.hexagrams.random()
                } while (segundoSorteado == sorteado)
            }

            // Define o tipo correto para não sobrepor na Home
            val tipoLeitura = if (currentMode == IChingMode.DAILY) "ICHING_DAY" else "ICHING_READING"

            leituraRepository.salvarLeituraIChing(
                tipo = tipoLeitura,
                hexagramNumber = sorteado.number,
                hexagramFutureNumber = segundoSorteado?.number,
                nota = _uiState.value.pergunta
            )

            // 🚀 Analytics
            if (currentMode == IChingMode.DAILY) {
                AnalyticsHelper.logIChingDailyViewed()
            }

            _uiState.update {
                it.copy(
                    drawState = DrawState.Result(
                        hexagram = sorteado, 
                        secondHexagram = segundoSorteado,
                        isSaved = true
                    ),
                    hexagramDoDia = if (currentMode == IChingMode.DAILY) sorteado else it.hexagramDoDia
                )
            }
        }
    }

    fun reset() {
        _uiState.update { 
            it.copy(
                drawState = DrawState.Intention,
                pergunta = ""
            )
        }
    }
}
