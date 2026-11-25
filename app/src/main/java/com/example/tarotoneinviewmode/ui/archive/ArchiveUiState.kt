package com.example.tarotoneinviewmode.ui.archive

import com.example.tarotoneinviewmode.domain.model.TarotCard

data class ArchiveUiState(
    val selectedTab: ArchiveTab = ArchiveTab.MAJOR,
    val cards: List<TarotCard> = emptyList(),
    val expandedCardId: String? = null
)
