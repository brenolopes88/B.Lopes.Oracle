package com.example.tarotoneinviewmode.domain.model

import androidx.compose.runtime.Immutable
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Suit

@Immutable
data class TarotCard(
    val id: String,
    val name: String,
    val arcana: ArcanaType,
    val suit: Suit?,
    val number: Int?,
    val essence: String,
    val lightMeaning: String,
    val shadowMeaning: String,
    val guidanceWhenReversed: String,
    val keywordsLight: List<String>,
    val keywordsShadow: List<String>,
    val correspondences: Correspondences,
    val history: String,
    val imageResId: Int,
    val affirmation: String? = null,
    // campos opcionais — invisíveis até você preencher
    val hinduArchetype: String? = null,
    val umbandasEntity: String? = null,
    val chakra: String? = null,
)