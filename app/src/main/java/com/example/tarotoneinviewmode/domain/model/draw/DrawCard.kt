package com.example.tarotoneinviewmode.domain.model.draw

import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.ui.draw.DrawPosition

data class DrawCard(
    val card: TarotCard,
    val position: DrawPosition,
    val isReversed: Boolean
)
