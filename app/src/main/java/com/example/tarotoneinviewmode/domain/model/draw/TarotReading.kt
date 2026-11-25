package com.example.tarotoneinviewmode.domain.model.draw

import java.util.UUID

data class TarotReading(
    val id: String = UUID.randomUUID().toString(),
    val cards: List<DrawCard> = emptyList(),
    val timestamp: Long = System.currentTimeMillis(),
    val note: String = ""
)
