package com.example.tarotoneinviewmode.ui.draw

import com.example.tarotoneinviewmode.domain.model.draw.DrawCard

data class DrawUiState(
    val mode: DrawMode = DrawMode.DAY_CARD,
    val cards: List<DrawCard> = emptyList(),
    // índice da próxima carta a ser revelada (começa em -1 = nenhuma revelada)
    val revealedCount: Int = 0,
    val isComplete: Boolean = false,
    val isDrawing: Boolean = false,
    val isShuffling: Boolean = false,
    val isCheckingDay: Boolean = false,
    val cartaDoDiaJaExistia: Boolean = false
) {
    val allRevealed: Boolean get() = cards.isNotEmpty() && revealedCount >= cards.size
    val hasCards: Boolean get() = cards.isNotEmpty()
}
