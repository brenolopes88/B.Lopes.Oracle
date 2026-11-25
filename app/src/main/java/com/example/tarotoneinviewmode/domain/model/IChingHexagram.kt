package com.example.tarotoneinviewmode.domain.model

import androidx.compose.runtime.Immutable

enum class Trigram(
    val title: String,
    val element: String,
    val symbol: String
) {
    HEAVEN("Céu", "Ch'ien", "☰"),
    EARTH("Terra", "K'un", "☷"),
    THUNDER("Trovão", "Chen", "☳"),
    WATER("Água", "K'an", "☵"),
    MOUNTAIN("Montanha", "Gen", "☶"),
    WIND("Vento", "Sun", "☴"),
    FIRE("Fogo", "Li", "☲"),
    LAKE("Lago", "Tui", "☱")
}

@Immutable
data class IChingHexagram(
    val number: Int,
    val nameOriginal: String,
    val nameTranslated: String,
    val pinyin: String,
    val unicodeSymbol: String,
    val trigramUpper: Trigram,
    val trigramLower: Trigram,
    val judgment: String,
    val imageText: String,
    val summary: String
)
