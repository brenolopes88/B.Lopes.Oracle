package com.example.tarotoneinviewmode.domain.enums

enum class Suit {
    WANDS,
    CUPS,
    SWORDS,
    PENTACLES;

    val displayName: String
        get() = when (this) {
            WANDS -> "Paus"
            CUPS -> "Copas"
            SWORDS -> "Espadas"
            PENTACLES -> "Ouros"
        }
}
