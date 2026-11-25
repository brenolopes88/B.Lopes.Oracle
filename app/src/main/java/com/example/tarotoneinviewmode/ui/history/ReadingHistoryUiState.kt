package com.example.tarotoneinviewmode.ui.history

import com.example.tarotoneinviewmode.domain.model.draw.TarotReading
import com.example.tarotoneinviewmode.domain.model.IChingHexagram

data class ReadingHistoryUiItem(
    val id: Long,
    val tipo: String,
    val timestamp: Long,
    val note: String,
    val tarotReading: TarotReading? = null,
    val hexagramaNumber: Int? = null,
    val hexagrama: IChingHexagram? = null,
    val hexagramaFutureNumber: Int? = null, // 🚀 Adicionado
    val hexagramaFuture: IChingHexagram? = null, // 🚀 Adicionado
    val isFavorite: Boolean = false
) {
    val modeLabel: String
        get() = when {
            hexagramaFutureNumber != null -> "I CHING · MUTAÇÃO"
            hexagramaNumber != null || tipo == "ICHING_DAY" -> "I CHING · HEXAGRAMA $hexagramaNumber"
            tarotReading != null -> when (tarotReading.cards.size) {
                1 -> "CARTA DO DIA"
                3 -> "PASSADO · PRESENTE · FUTURO"
                else -> "LEITURA"
            }
            else -> "LEITURA"
        }
}

enum class HistoryFilter { ALL, TAROT, ICHING, FAVORITES }

data class ReadingHistoryUiState(
    val readings: List<ReadingHistoryUiItem> = emptyList(),
    val currentFilter: HistoryFilter = HistoryFilter.ALL
)